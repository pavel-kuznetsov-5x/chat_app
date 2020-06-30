package com.spqrta.chatapp.repository

import com.spqrta.chatapp.entity.Chat
import com.spqrta.chatapp.entity.Message
import com.spqrta.chatapp.entity.User
import com.spqrta.chatapp.network.Api
import com.spqrta.chatapp.network.RequestManager
import com.spqrta.chatapp.utility.utils.applyGlobalTransformer
import com.spqrta.chatapp.utility.utils.applySchedulers
import io.reactivex.Observable
import io.reactivex.Single

object MessagesRepository {

    fun getMessages(chat: Chat): Single<List<Message>> {
        return RequestManager.service
            .getMessages(chat.id)
            .applyGlobalTransformer()
    }

    fun sendMessage(to: Chat, text: String): Single<Message> {
        return RequestManager.service
            .sendMessage(Api.MessageBody(to.id, text))
            .applyGlobalTransformer()
    }

}