package com.example.streetchampionproject.app.di

import com.example.streetchampionproject.api.apiFactory.di.ApiFactoryModule
import com.example.streetchampionproject.api.scs.di.StreetChampionModule
import com.example.streetchampionproject.app.scope.ApplicationScope
import com.example.streetchampionproject.loginActivity.di.LoginFeatureComponent
import com.example.streetchampionproject.registerActivity.di.RegisterFeatureComponent
import dagger.Component

@ApplicationScope
@Component(modules = [NavigatorModule::class, ApiFactoryModule::class])
interface AppComponent {

    fun loginFeatureComponent(): LoginFeatureComponent

    fun registerActivityComponent(): RegisterFeatureComponent

}
