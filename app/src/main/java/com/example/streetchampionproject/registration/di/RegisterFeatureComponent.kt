package com.example.streetchampionproject.registration.di

import com.example.streetchampionproject.registration.presentation.RegisterActivity
import com.example.streetchampionproject.registration.di.scope.RegisterActivityScope
import dagger.Subcomponent

@RegisterActivityScope
@Subcomponent(modules = [RegisterFeatureModule::class])
interface RegisterFeatureComponent {

    fun inject(registerActivity: RegisterActivity)

    @Subcomponent.Builder
    interface Builder {

        fun build(): RegisterFeatureComponent

    }

}
