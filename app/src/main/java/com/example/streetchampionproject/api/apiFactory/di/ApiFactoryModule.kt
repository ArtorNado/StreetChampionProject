package com.example.streetchampionproject.api.apiFactory.di

import com.example.streetchampionproject.api.apiFactory.ApiFactory
import com.example.streetchampionproject.api.apiFactory.authenticator.TokenAuthenticator
import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.app.di.scope.ApplicationScope
import com.example.streetchampionproject.common.sharedPreference.LocalStorage
import dagger.Module
import dagger.Provides

@Module
class ApiFactoryModule {

    @ApplicationScope
    @Provides
    fun provideTokenAuth(localStorage: LocalStorage) = TokenAuthenticator(localStorage)

    @ApplicationScope
    @Provides
    fun provideApiFactory(tokenAuthenticator: TokenAuthenticator) = ApiFactory(tokenAuthenticator)

    @ApplicationScope
    @Provides
    fun provideStreetChampionService(apiFactory: ApiFactory): StreetChampionService = apiFactory.streetChampionService
}

