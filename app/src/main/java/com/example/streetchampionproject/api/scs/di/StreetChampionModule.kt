package com.example.streetchampionproject.api.scs.di

import com.example.streetchampionproject.api.apiFactory.ApiFactory
import com.example.streetchampionproject.app.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class StreetChampionModule(private val apiFactory: ApiFactory) {

    @ApplicationScope
    @Provides
    fun provideService() = apiFactory.streetChampionService
}
