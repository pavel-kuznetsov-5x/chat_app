package com.spqrta.chatapp.entity

import com.spqrta.chatapp.repository.UserRepository

class User(
    val id: Int,
    val displayName: String,
    val avatarUrl: String
) {
    companion object {
        fun test() = if(Math.random() > 0.5) {
            UserRepository.USER_1
        } else {
            UserRepository.USER_2
        }
    }
}