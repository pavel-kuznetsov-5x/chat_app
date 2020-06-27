package com.spqrta.chatapp.network

import com.google.gson.annotations.SerializedName
import com.spqrta.chatapp.entity.Chat
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface Api {

    @POST("/api/auth")
    fun login(@Body loginBody: LoginBody): Single<LoginResponse>
    class LoginBody(
        val username: String,
        val password: String
    )
    class LoginResponse(
        val token: String,
        @SerializedName("user_id") val userId: Int
    )

    @GET("/api/chats")
    fun getUserChats(): Single<List<Chat>>

}