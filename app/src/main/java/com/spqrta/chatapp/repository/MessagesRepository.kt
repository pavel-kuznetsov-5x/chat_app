package com.spqrta.chatapp.repository

import com.spqrta.chatapp.entity.Chat
import com.spqrta.chatapp.entity.Message
import com.spqrta.chatapp.entity.User
import io.reactivex.Observable
import io.reactivex.Single

object MessagesRepository {

    fun getMessages(chat: Chat): Single<List<Message>> {
        return Single.just(Observable.fromCallable {
            Message.test(chat)
        }.repeat(50).toList().blockingGet())
    }

    fun sendMessage(to: Chat, text: String): Single<Message> {
        return Single.just(
            Message(
                text,
                UserRepository.currentUser!!,
                to
            )
        )
    }

}