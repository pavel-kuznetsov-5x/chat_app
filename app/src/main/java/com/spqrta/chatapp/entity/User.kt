package com.spqrta.chatapp.entity

class User(
    val displayName: String,
    val avatarUrl: String
) {
    companion object{
        fun test() = User(
            "test user ${Math.random()}",
            "https://miro.medium.com/max/256/1*d69DKqFDwBZn_23mizMWcQ.png"
        )
    }
}