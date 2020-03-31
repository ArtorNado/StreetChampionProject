package com.example.streetchampionproject.loginActivity.di

import com.example.streetchampionproject.loginActivity.scope.LoginActivityScope
import com.example.streetchampionproject.loginActivity.presentation.LoginActivity
import dagger.Subcomponent

@LoginActivityScope
@Subcomponent(modules = [LoginFeatureModule::class])
interface LoginFeatureComponent {

    fun injectLoginActivity(loginActivity: LoginActivity)

}
