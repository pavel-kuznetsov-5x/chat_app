package com.spqrta.chatapp.repository

import com.spqrta.chatapp.entity.Chat
import com.spqrta.chatapp.entity.Message
import com.spqrta.chatapp.entity.User
import com.spqrta.chatapp.network.RequestManager
import com.spqrta.chatapp.utility.utils.applySchedulers
import io.reactivex.Observable
import io.reactivex.Single

object MessagesRepository {

    fun getMessages(chat: Chat): Single<List<Message>> {
//        return Single.just(Observable.fromCallable {
//            Message.test(chat)
//        }.repeat(50).toList().blockingGet())
        return RequestManager.service.getMessages(chat.id).applySchedulers()
    }

    fun sendMessage(to: Chat, text: String): Single<Message> {
//        return Single.just(
//            Message(
//                text,
//                UserRepository.currentUser!!,
//                to
//            )
//        )
        return Single.never()
    }

}