package com.spqrta.chatapp.screens.chat

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spqrta.chatapp.utility.recycler.BaseAdapter
import kotlinx.android.extensions.LayoutContainer
import com.spqrta.chatapp.R
import com.spqrta.chatapp.entity.Chat
import com.spqrta.chatapp.entity.Message
import com.spqrta.chatapp.repository.MessagesRepository
import com.spqrta.chatapp.utility.Logger
import kotlinx.android.synthetic.main.item_message.view.*

@SuppressLint("CheckResult")
class MessagesAdapter(val chat: Chat, logView: TextView? = null) :
    BaseAdapter<Message, MessagesAdapter.VhMessage>() {

    override val itemLayoutResource: Int = R.layout.item_message
    private var isLoading: Boolean = false
    private lateinit var recyclerView: RecyclerView

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val loadedItemCount = recyclerView.adapter!!.itemCount
            val visibleItemCount = recyclerView.layoutManager!!.childCount
            val lm = (recyclerView.layoutManager as LinearLayoutManager?)!!

            //visible window
            val topVisibleItemPosition = lm.findFirstVisibleItemPosition()
            val bottomVisibleItemPosition = lm.findLastVisibleItemPosition()

            val topLoaded = loadedItemCount
            val topVisibleItem = items[topVisibleItemPosition]
            val bottomVisibleItem = items[bottomVisibleItemPosition]

            if(!isLoading) {
                if (topVisibleItemPosition <= LOAD_OFFSET/* || topVisibleItem.id != chat.firstMessageId*/) {
                    loadMore(top = true)
                }

                val bottomRangeEndDistance = (itemCount - bottomVisibleItemPosition)
                if (bottomRangeEndDistance <= LOAD_OFFSET && bottomVisibleItem.id != chat.lastMessageId) {
                    Logger.d("load bottom $bottomRangeEndDistance ${bottomVisibleItem.id} ${chat.lastMessageId}")
                    loadMore(top = false)
                }
                if (itemCount - bottomVisibleItemPosition > DELETE_OFFSET) {
                    items.removeAll(
                        items.subList(
                            bottomVisibleItemPosition + DELETE_OFFSET,
                            items.size
                        )
                    )
                }
                if (topVisibleItemPosition > DELETE_OFFSET) {
                    items.removeAll(
                        items.subList(
                            topVisibleItemPosition - DELETE_OFFSET,
                            items.size
                        )
                    )
                }

                var s = "visible: ${topVisibleItem.id} - ${bottomVisibleItem.id} | $visibleItemCount\n"
                s += "loaded: ${items.first().id} - ${items.last().id} | ${items.size}\n"
                s += "total: ${chat.messageCount}\n"
                s += "${itemCount - bottomVisibleItemPosition}\n"
                logView?.setText(s)
            }
        }
    }

    fun loadMore(top: Boolean) {
        isLoading = true
        MessagesRepository.getMessages(
            chat,
            FETCH,
            items[0].id,
            older = top
        ).subscribe { it: List<Message> ->
            recyclerView.post {
                if(top) {
                    items.addAll(0, it)
                    notifyItemRangeInserted(0, it.size)
                } else {
                    val oldSize = items.size
                    items.addAll(it)
                    notifyItemRangeInserted(oldSize, it.size)
                }
                isLoading = false
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].isMy()) {
            ITEM_TYPE_MY
        } else {
            ITEM_TYPE_OTHER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhMessage {
        return createViewHolder(
            LayoutInflater.from(parent.context).inflate(
                when (viewType) {
                    ITEM_TYPE_MY -> R.layout.item_my_message
                    else -> R.layout.item_message
                },
                parent,
                false
            )
        ) { position ->
            onItemClickListener?.invoke(items[position])
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun createViewHolder(view: View, baseClickListener: (Int) -> Unit): VhMessage {
        return VhMessage(view, baseClickListener)
    }

    class VhMessage(override val containerView: View, clickListener: (Int) -> Unit) :
        BaseAdapter.BaseVh<Message>(containerView, clickListener), LayoutContainer {

        override fun bind(item: Message) {
            containerView.tvMessageText.text = item.text
        }
    }

    companion object {
        const val ITEM_TYPE_MY = 0
        const val ITEM_TYPE_OTHER = 1
        const val FETCH = 20
        const val LOAD_OFFSET = 10
        const val DELETE_OFFSET = 30
    }

}