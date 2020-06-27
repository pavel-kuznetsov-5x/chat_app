package com.spqrta.chatapp.repository

import com.spqrta.chatapp.entity.Chat
import com.spqrta.chatapp.entity.Message
import com.spqrta.chatapp.network.RequestManager
import com.spqrta.chatapp.utility.utils.applySchedulers
import io.reactivex.Observable
import io.reactivex.Single

object ChatsRepository {

    fun getChats(): Single<List<Chat>> {
//        return Single.just(Observable.fromCallable {
//            Chat.test()
//        }.repeat(50).toList().blockingGet())
        return RequestManager.service.getUserChats().applySchedulers()
    }

}