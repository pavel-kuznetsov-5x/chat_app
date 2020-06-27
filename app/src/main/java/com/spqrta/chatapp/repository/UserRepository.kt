package com.spqrta.chatapp.repository

import com.google.gson.Gson
import com.spqrta.chatapp.entity.User
import com.spqrta.chatapp.utility.utils.StringSetting
import io.reactivex.Single

object UserRepository {

    private val userSetting = object : StringSetting() {
        override val key = "user"
    }

    val currentUser: User? = userSetting.load()?.let {
        Gson().fromJson(it, User::class.java)
    }

    fun login(): Single<User> {
        val user = USER_1
        userSetting.save(Gson().toJson(user))
        return Single.just(user)
    }

    fun isLoggedIn(): Boolean {
        return currentUser != null
    }

    val USER_1 = User(
        0,
        "test user 1",
        "https://miro.medium.com/max/256/1*d69DKqFDwBZn_23mizMWcQ.png"
    )

    val USER_2 = User(
        1,
        "test user 2",
        "https://miro.medium.com/max/256/1*d69DKqFDwBZn_23mizMWcQ.png"
    )
}