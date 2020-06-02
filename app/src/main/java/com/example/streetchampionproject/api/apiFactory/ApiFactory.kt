package com.example.streetchampionproject.api.apiFactory

import com.example.streetchampionproject.BuildConfig
import com.example.streetchampionproject.api.apiFactory.authenticator.TokenAuthenticator
import com.example.streetchampionproject.clubPage.data.network.ClubPageService
import com.example.streetchampionproject.clubs.data.network.ClubsService
import com.example.streetchampionproject.commandMatch.data.network.CommandMatchService
import com.example.streetchampionproject.creating.createMatch.data.network.CreateMatchService
import com.example.streetchampionproject.creating.createTeam.data.network.CreateTeamService
import com.example.streetchampionproject.login.data.network.LoginService
import com.example.streetchampionproject.matchHistory.data.network.MatchHistoryService
import com.example.streetchampionproject.matches.data.network.MatchListService
import com.example.streetchampionproject.notification.data.network.NotificationService
import com.example.streetchampionproject.profile.data.network.ProfileService
import com.example.streetchampionproject.registration.data.network.RegisterService
import com.example.streetchampionproject.singleMatch.data.network.ParticipantService
import com.example.streetchampionproject.singleMatch.data.network.SingleMatchService
import com.example.streetchampionproject.teamSquad.data.network.SquadService
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


    val clubPageService: ClubPageService by lazy {
        retrofit.create(
            ClubPageService::class.java
        )
    }

    val createMatchService: CreateMatchService by lazy {
        retrofit.create(
            CreateMatchService::class.java
        )
    }

    val createTeamService: CreateTeamService by lazy {
        retrofit.create(
            CreateTeamService::class.java
        )
    }

    val loginService: LoginService by lazy {
        retrofit.create(
            LoginService::class.java
        )
    }

    val clubsService: ClubsService by lazy {
        retrofit.create(
            ClubsService::class.java
        )
    }

    val profileService: ProfileService by lazy {
        retrofit.create(
            ProfileService::class.java
        )
    }

    val commandMatchService: CommandMatchService by lazy {
        retrofit.create(
            CommandMatchService::class.java
        )
    }

    val matchListService: MatchListService by lazy {
        retrofit.create(
            MatchListService::class.java
        )
    }

    val notificationService: NotificationService by lazy {
        retrofit.create(
            NotificationService::class.java
        )
    }

    val registerService: RegisterService by lazy {
        retrofit.create(
            RegisterService::class.java
        )
    }

    val participantService: ParticipantService by lazy {
        retrofit.create(
            ParticipantService::class.java
        )
    }

    val singleMatchService: SingleMatchService by lazy {
        retrofit.create(
            SingleMatchService::class.java
        )
    }

    val squadService: SquadService by lazy {
        retrofit.create(
            SquadService::class.java
        )
    }

    val matchHistoryService: MatchHistoryService by lazy {
        retrofit.create(
            MatchHistoryService::class.java
        )
    }

}

