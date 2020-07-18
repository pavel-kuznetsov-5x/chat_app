package com.spqrta.chatapp.repository

import com.spqrta.chatapp.Dependencies
import com.spqrta.chatapp.entity.Chat
import com.spqrta.chatapp.entity.Message
import com.spqrta.chatapp.network.Api
import com.spqrta.chatapp.utility.utils.applyGlobalTransformer
import io.reactivex.Observable
import io.reactivex.Single

object MessagesRepository {

    private var nextId = 0

    val testMessages = Observable.fromCallable {
        val mess = Message(
            id = nextId,
            from = UserRepository.currentUser,
            text = "Text ${nextId}"
        )
        nextId++
        mess
    }.repeat(1000).toList().blockingGet()

    fun getMessages(chat: Chat): Single<List<Message>> {
        return Dependencies.api
            .getMessages(chat.id)
            .applyGlobalTransformer()
    }

    fun getMessages(
        chat: Chat,
        fetch: Int,
        lastMessageId: Int? = null,
        older: Boolean = true
    ): Single<List<Message>> {
        return Single.just(
            if (lastMessageId != null) {
                testMessages.filter {
                    if (older) {
                        it.id < lastMessageId
                    } else {
                        it.id > lastMessageId
                    }
                }.takeLast(fetch)
            } else {
                testMessages.takeLast(fetch)
            }
        )
    }

    fun sendMessage(to: Chat, text: String): Single<Message> {
        return Dependencies.api
            .sendMessage(Api.MessageBody(to.id, text))
            .applyGlobalTransformer()
    }

}