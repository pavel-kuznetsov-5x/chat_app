package com.spqrta.chatapp.repository

import com.google.gson.Gson
import com.spqrta.chatapp.entity.User
import com.spqrta.chatapp.network.Api
import com.spqrta.chatapp.network.RequestManager
import com.spqrta.chatapp.utility.Toaster
import com.spqrta.chatapp.utility.utils.StringSetting
import com.spqrta.chatapp.utility.utils.applySchedulers
import io.reactivex.Single

object UserRepository {

    private val tokenSetting = object : StringSetting() {
        override val key = "token"
    }

    private val userSetting = object : StringSetting() {
        override val key = "user"
    }

    val currentUser: User? = userSetting.load()?.let {
        Gson().fromJson(it, User::class.java)
    }

    val token: String?
        get() = tokenSetting.load()

    fun login(): Single<User> {
        /*val user = USER_1
        userSetting.save(Gson().toJson(user))
        return Single.just(user)*/
        return RequestManager.service.login(Api.LoginBody(
            "spqrta",
            "007007"
        )).applySchedulers()
            .map {
                //todo
                val user = User.test().copy(id = it.userId)
                userSetting.save(Gson().toJson(user))
                tokenSetting.save(it.token)
                user
            }
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