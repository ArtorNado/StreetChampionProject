package com.example.streetchampionproject.registerActivity.di

import com.example.streetchampionproject.registerActivity.presentation.RegisterActivity
import com.example.streetchampionproject.registerActivity.scope.RegisterActivityScope
import dagger.Subcomponent

@RegisterActivityScope
@Subcomponent(modules = [RegisterFeatureModule::class])
interface RegisterFeatureComponent {

    fun injectRegisterActivity(registerActivity: RegisterActivity)
}
