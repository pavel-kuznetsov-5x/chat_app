package com.spqrta.chatapp

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.spqrta.chatapp.utility.Logger

class FirebaseService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        Logger.d("message")
    }

    override fun onNewToken(token: String) {
        Logger.d(token)
    }
}