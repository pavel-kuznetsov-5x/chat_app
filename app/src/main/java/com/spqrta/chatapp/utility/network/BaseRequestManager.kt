package com.spqrta.chatapp.utility.network

import com.spqrta.chatapp.utility.CustomApplication
import com.spqrta.chatapp.utility.Logger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Suppress("LeakingThis")
abstract class BaseRequestManager {

    abstract val baseUrl: String

    val retrofit: Retrofit

    init {
        val interceptor = HttpLoggingInterceptor { message ->
            if (CustomApplication.appConfig.debugMode) {
                Logger.v(message)
            }
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val clientBuilder = OkHttpClient.Builder()

        buildClient(clientBuilder)
        if (CustomApplication.appConfig.debugMode) {
            clientBuilder.addInterceptor(interceptor)
        }


        val client = clientBuilder
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
//            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
//            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    }

    open fun buildClient(builder: OkHttpClient.Builder) {

    }


}