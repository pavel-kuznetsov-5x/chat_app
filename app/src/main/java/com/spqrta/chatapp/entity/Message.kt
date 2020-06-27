package com.spqrta.chatapp.entity

import android.os.Parcelable
import com.spqrta.chatapp.repository.UserRepository
import kotlinx.android.parcel.Parcelize

@Parcelize
class Message(
    val text: String,
    val from: User,
    val chat: Chat
): Parcelable {

    fun isMy(): Boolean {
        return UserRepository.currentUser?.let {
            from.id == it.id
        } ?: false
    }

    companion object {
        fun test(chat: Chat) = Message(
            "test message ${Math.random()}",
            User.test(),
            chat
        )
    }
}