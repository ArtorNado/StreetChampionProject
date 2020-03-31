package com.example.streetchampionproject.app.di

import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.app.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class NavigatorModule {

    @ApplicationScope
    @Provides
    fun provideNavigator() = Navigator()
}
