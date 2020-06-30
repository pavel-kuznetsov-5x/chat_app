package com.spqrta.chatapp.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.spqrta.chatapp.repository.UserRepository
import kotlinx.android.parcel.Parcelize

@Parcelize
class Message(
    val id: Int,
    val text: String,
    @SerializedName("author") val from: User
//    val chat: Chat
): Parcelable {

    fun isMy(): Boolean {
        return UserRepository.currentUser?.let {
            from.id == it.id
        } ?: false
    }

    companion object {
        fun test(chat: Chat) = Message(
            0,
            "test message ${Math.random()}",
            User.test()
//            chat
        )
    }
}