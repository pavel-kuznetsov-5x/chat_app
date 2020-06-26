package com.spqrta.chatapp.entity

class Message(
    val text: String,
    val from: User
) {

    companion object {
        fun test() = Message(
            "test message ${Math.random()}",
            User.test()
        )
    }
}