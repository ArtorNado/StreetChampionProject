package com.example.streetchampionproject.api.apiFactory

import com.example.streetchampionproject.BuildConfig
import com.example.streetchampionproject.api.apiFactory.authenticator.TokenAuthenticator
import com.example.streetchampionproject.api.scs.StreetChampionService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory(
    private val tokenAuthenticator: TokenAuthenticator
) {

    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url().newBuilder()
            .build()

        val newRequest = chain.request().newBuilder()
            .url(newUrl)
            .build()
        chain.proceed(newRequest)
    }
    private val client = OkHttpClient().newBuilder()
        .authenticator(tokenAuthenticator)
        .addInterceptor(authInterceptor)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    val streetChampionService: StreetChampionService by lazy {
        retrofit.create(
            StreetChampionService::class.java
        )
    }
}

