package com.spqrta.chatapp.network

import com.spqrta.chatapp.repository.UserRepository
import com.spqrta.chatapp.utility.network.BaseRequestManager
import okhttp3.OkHttpClient

object RequestManager: BaseRequestManager() {

    override val baseUrl: String = "http://10.0.2.2:8000"

    val service by lazy {
        retrofit.create(Api::class.java)
    }

    override fun buildClient(builder: OkHttpClient.Builder) {
        builder.addInterceptor { chain ->
            if(UserRepository.token != null) {
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "Token ${UserRepository.token}")
                    .build()
                return@addInterceptor chain.proceed(request)
            } else {
                return@addInterceptor  chain.proceed(chain.request())
            }
        }
    }
}