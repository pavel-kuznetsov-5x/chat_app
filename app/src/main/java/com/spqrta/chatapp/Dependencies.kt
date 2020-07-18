package com.spqrta.chatapp

import com.spqrta.chatapp.network.Api
import com.spqrta.chatapp.network.RequestManager
import com.spqrta.chatapp.repository.ChatsRepository
import com.spqrta.chatapp.repository.MessagesRepository

object Dependencies {

    var api: Api = RequestManager.service

}