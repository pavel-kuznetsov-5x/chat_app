package com.spqrta.chatapp.network

import com.google.gson.annotations.SerializedName
import com.spqrta.chatapp.entity.Chat
import com.spqrta.chatapp.entity.Message
import com.spqrta.chatapp.entity.User
import com.spqrta.chatapp.utility.utils.Stub
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
        @SerializedName("user") val user: User
    )

    @GET("/api/chats")
    fun getUserChats(): Single<List<Chat>>

    @GET("/api/messages")
    fun getMessages(@Query("chat_id") chatId: Int): Single<List<Message>>

    @POST("/api/messages/")
    fun sendMessage(@Body body: MessageBody): Single<Message>
    class MessageBody(
        val chat: Int,
        val text: String
    )

    @POST("/fcm/")
    fun updateFcmToken(@Body body: TokenBody): Single<Stub>
    class TokenBody(
        val token: String
    )

}