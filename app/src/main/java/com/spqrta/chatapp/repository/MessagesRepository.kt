package com.spqrta.chatapp.repository

import com.spqrta.chatapp.entity.Chat
import com.spqrta.chatapp.entity.Message
import io.reactivex.Observable
import io.reactivex.Single

object MessagesRepository {

    fun getMessages(chatId: Int): Single<List<Message>> {
        return Single.just(Observable.fromCallable {
            Message.test()
        }.repeat(50).toList().blockingGet())
    }

}