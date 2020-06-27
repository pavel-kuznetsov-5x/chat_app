package com.spqrta.chatapp.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Chat(
    val id: Int,
    val name: String,
    //todo null
    val avatarUrl: String,
    var lastMessage: Message? = null
): Parcelable {
    companion object {
        fun test(): Chat {
            val chat = Chat(
                (Math.random() * 1000).toInt(),
                "chat ${Math.random()}",
                "https://miro.medium.com/max/256/1*d69DKqFDwBZn_23mizMWcQ.png"
            )
            chat.lastMessage = Message.test(chat)
            return chat
        }
    }
}
