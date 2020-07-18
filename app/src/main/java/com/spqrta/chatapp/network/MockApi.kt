package com.spqrta.chatapp.network

import com.spqrta.chatapp.entity.Chat
import com.spqrta.chatapp.entity.Message
import com.spqrta.chatapp.utility.utils.Stub
import io.reactivex.Single

object MockApi: Api {
    override fun login(loginBody: Api.LoginBody): Single<Api.LoginResponse> {
        return Single.never()
    }

    override fun getUserChats(): Single<List<Chat>> {
        return Single.never()
    }

    override fun getMessages(chatId: Int): Single<List<Message>> {
        return Single.just(listOf(Message.test()))
    }

    override fun sendMessage(body: Api.MessageBody): Single<Message> {
        return Single.never()
    }

    override fun updateFcmToken(body: Api.TokenBody): Single<Stub> {
        return Single.never()
    }
}