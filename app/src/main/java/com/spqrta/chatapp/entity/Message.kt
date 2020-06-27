package com.spqrta.chatapp.entity

import com.spqrta.chatapp.repository.UserRepository

class Message(
    val text: String,
    val from: User
) {

    fun isMy(): Boolean {
        return UserRepository.currentUser?.let {
            from.id == it.id
        } ?: false
    }

    companion object {
        fun test() = Message(
            "test message ${Math.random()}",
            User.test()
        )
    }
}