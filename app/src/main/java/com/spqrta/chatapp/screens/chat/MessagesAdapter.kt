package com.spqrta.chatapp.screens.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spqrta.chatapp.utility.recycler.BaseAdapter
import kotlinx.android.extensions.LayoutContainer
import com.spqrta.chatapp.R
import com.spqrta.chatapp.entity.Message
import com.spqrta.chatapp.repository.UserRepository
import kotlinx.android.synthetic.main.item_message.view.*

class MessagesAdapter : BaseAdapter<Message, MessagesAdapter.VhMessage>() {

    override val itemLayoutResource: Int = R.layout.item_message

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
    }

}