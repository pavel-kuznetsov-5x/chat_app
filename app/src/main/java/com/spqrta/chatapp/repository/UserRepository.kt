package com.spqrta.chatapp.repository

import com.google.gson.Gson
import com.spqrta.chatapp.entity.User
import com.spqrta.chatapp.network.Api
import com.spqrta.chatapp.network.RequestManager
import com.spqrta.chatapp.utility.Toaster
import com.spqrta.chatapp.utility.utils.StringSetting
import com.spqrta.chatapp.utility.utils.Stub
import com.spqrta.chatapp.utility.utils.applySchedulers
import io.reactivex.Single

object UserRepository {

    private val tokenSetting = object : StringSetting() {
        override val key = "token"
    }

    private val userSetting = object : StringSetting() {
        override val key = "user"
    }

    val currentUser: User?
        get() = userSetting.load()?.let {
            Gson().fromJson(it, User::class.java)
        }

    val token: String?
        get() = tokenSetting.load()

    fun login(username: String, password: String): Single<Stub> {
        /*val user = USER_1
        userSetting.save(Gson().toJson(user))
        return Single.just(user)*/
        return RequestManager.service
            .login(Api.LoginBody(username, password))
            .applySchedulers()
            .map {
                userSetting.save(Gson().toJson(it.user))
                tokenSetting.save(it.token)
                Stub
            }
    }

    fun isLoggedIn(): Boolean {
        return currentUser != null
    }

    fun logout() {
        tokenSetting.save(null)
        userSetting.save(null)
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