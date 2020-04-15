package com.example.streetchampionproject.api.apiFactory.di

import com.example.streetchampionproject.api.apiFactory.ApiFactory
import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.app.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApiFactoryModule {

    @ApplicationScope
    @Provides
    fun provideApiFactory() = ApiFactory

    @ApplicationScope
    @Provides
    fun provideStreetChampionService(): StreetChampionService = ApiFactory.streetChampionService
}

