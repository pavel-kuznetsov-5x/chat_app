package com.spqrta.chatapp.screens.chats

import android.view.View
import com.bumptech.glide.Glide
import com.spqrta.chatapp.entity.Chat
import com.spqrta.chatapp.utility.recycler.BaseAdapter
import kotlinx.android.extensions.LayoutContainer
import com.spqrta.chatapp.R
import kotlinx.android.synthetic.main.item_chat.view.*

class ChatsAdapter: BaseAdapter<Chat, ChatsAdapter.VhChat>() {

    override val itemLayoutResource: Int = R.layout.item_chat

    override fun createViewHolder(view: View, baseClickListener: (Int) -> Unit): VhChat {
        return VhChat(view, baseClickListener)
    }

    class VhChat(override val containerView: View, clickListener: (Int) -> Unit): BaseVh<Chat>(containerView, clickListener), LayoutContainer {

        override fun bind(item: Chat) {
            containerView.tvChatName.text = item.displayName
            //todo
            if(item.lastMessage != null) {
                containerView.tvChatLastMessage.text =
                    "${item.lastMessage?.from?.displayName}: ${item.lastMessage?.text}"
            } else {
                containerView.tvChatLastMessage.text = "No messages yet"
            }
            Glide.with(containerView.context).load(item.avatarUrl).into(containerView.ivChatAvatar)
        }
    }

}