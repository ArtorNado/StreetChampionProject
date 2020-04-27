package com.example.streetchampionproject.app.di

import com.example.streetchampionproject.api.apiFactory.di.ApiFactoryModule
import com.example.streetchampionproject.app.App
import com.example.streetchampionproject.app.di.scope.ApplicationScope
import com.example.streetchampionproject.clubPage.di.ClubPageFeatureComponent
import com.example.streetchampionproject.clubPage.presentation.ui.squad.di.SquadFeatureComponent
import com.example.streetchampionproject.common.ViewModelFactoryModule
import com.example.streetchampionproject.common.di.LocalStorageModule
import com.example.streetchampionproject.login.di.LoginFeatureComponent
import com.example.streetchampionproject.main.presentation.ui.clubs.di.ClubListFeatureComponent
import com.example.streetchampionproject.main.presentation.ui.profile.di.ProfileFeatureComponent
import com.example.streetchampionproject.registration.di.RegisterFeatureComponent
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [NavigatorModule::class, ApiFactoryModule::class,
    ViewModelFactoryModule::class, LocalStorageModule::class])
interface AppComponent {

    fun loginFeatureComponent(): LoginFeatureComponent.Builder

    fun registerFeatureComponent(): RegisterFeatureComponent.Builder

    fun profileFeatureComponent(): ProfileFeatureComponent.Builder

    fun provideClubPageFeatureComponent():  ClubPageFeatureComponent.Builder

    fun provideSquadFeatureComponent(): SquadFeatureComponent.Builder

    fun provideClubListFeatureComponent(): ClubListFeatureComponent.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }
}
