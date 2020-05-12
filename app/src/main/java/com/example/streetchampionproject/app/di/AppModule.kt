package com.example.streetchampionproject.app.di

import android.content.Context
import com.example.streetchampionproject.app.App
import com.example.streetchampionproject.app.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @ApplicationScope
    @Provides
    fun bindContext(application: App): Context = application.applicationContext
}
