package com.spqrta.chatapp

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.spqrta.chatapp.network.Api
import com.spqrta.chatapp.network.RequestManager
import com.spqrta.chatapp.utility.Logger
import com.spqrta.chatapp.utility.utils.applyGlobalTransformer

class FirebaseService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        Logger.d("message")
    }

    override fun onNewToken(token: String) {
        RequestManager.service.updateFcmToken(Api.TokenBody(token)).applyGlobalTransformer().subscribe()
    }
}