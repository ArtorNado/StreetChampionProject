package com.example.streetchampionproject.app.di

import android.content.Context
import com.example.streetchampionproject.app.App
import com.example.streetchampionproject.app.di.scope.ApplicationScope
import com.example.streetchampionproject.common.sharedPreference.LocalStorage
import com.example.streetchampionproject.common.sharedPreference.SharedPrefStorage
import dagger.Module
import dagger.Provides

@Module
class LocalStorageModule {

    @ApplicationScope
    @Provides
    fun bindContext(application: App): Context = application.applicationContext

    @ApplicationScope
    @Provides
    fun provideLocalStorage(context: Context): LocalStorage =
        SharedPrefStorage(
            context
        )

}
