package com.spqrta.chatapp.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.spqrta.chatapp.repository.MessagesRepository
import kotlinx.android.parcel.Parcelize

@Parcelize
class Chat(
    val id: Int,
    @SerializedName("display_name") val displayName: String,
    //todo null
    @SerializedName("avatar_url") val avatarUrl: String,
    val messageCount: Int,
    val firstMessageId: Int? = null,
    val lastMessageId: Int? = null,
    var lastMessage: Message? = null
): Parcelable {
    companion object {
        fun test(): Chat {
            val chat = Chat(
                (Math.random() * 1000).toInt(),
                "chat ${Math.random()}",
                AVATAR,
                1000,
                firstMessageId = MessagesRepository.testMessages.first().id,
                lastMessageId = MessagesRepository.testMessages.last().id
            )
            chat.lastMessage = Message.test()
            return chat
        }

        const val AVATAR = "https://miro.medium.com/max/256/1*d69DKqFDwBZn_23mizMWcQ.png"
    }
}
