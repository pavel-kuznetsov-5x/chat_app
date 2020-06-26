package com.spqrta.chatapp.entity

class Chat(
    val name: String,
    //todo null
    val lastMessage: Message,
    val avatarUrl: String
) {
    companion object {
        fun test() = Chat(
            "chat ${Math.random()}",
            Message.test(),
            "https://miro.medium.com/max/256/1*d69DKqFDwBZn_23mizMWcQ.png"
        )
    }
}
