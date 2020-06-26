package com.spqrta.chatapp.repository

import com.spqrta.chatapp.entity.Chat
import com.spqrta.chatapp.entity.Message
import io.reactivex.Observable
import io.reactivex.Single

object ChatsRepository {

    fun getChats(): Single<List<Chat>> {
        return Single.just(Observable.fromCallable {
            Chat.test()
        }.repeat(50).toList().blockingGet())
    }

}