package com.spqrta.chatapp.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Chat(
    val id: Int,
    @SerializedName("display_name") val displayName: String,
    //todo null
    @SerializedName("avatar_url") val avatarUrl: String,
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
