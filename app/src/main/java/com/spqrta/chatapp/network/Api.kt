package com.spqrta.chatapp.network

import com.google.gson.annotations.SerializedName
import com.spqrta.chatapp.entity.Chat
import com.spqrta.chatapp.entity.Message
import io.reactivex.Single
import retrofit2.http.*

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

    @GET("/api/messages")
    fun getMessages(@Query("chat_id") chatId: Int): Single<List<Message>>

}