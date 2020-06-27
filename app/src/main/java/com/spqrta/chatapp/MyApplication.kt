package com.spqrta.chatapp

import com.spqrta.chatapp.utility.CustomApplication

class MyApplication : CustomApplication() {
    override fun createAppConfig(): AppConfig = if (!BuildConfig.DEBUG) {
        AppConfig()
    } else {
        AppConfig(debugMode = true)
    }
}