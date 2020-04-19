package com.example.streetchampionproject.login.di

import com.example.streetchampionproject.login.di.scope.LoginActivityScope
import com.example.streetchampionproject.login.presentation.LoginActivity

import dagger.Subcomponent

@LoginActivityScope
@Subcomponent(modules = [LoginFeatureModule::class])
interface LoginFeatureComponent {

    fun inject(loginActivity: LoginActivity)

    @Subcomponent.Builder
    interface Builder {

        fun build(): LoginFeatureComponent

    }

}
