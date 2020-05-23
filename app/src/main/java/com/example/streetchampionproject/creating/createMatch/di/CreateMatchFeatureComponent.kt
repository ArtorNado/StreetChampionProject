package com.example.streetchampionproject.creating.createMatch.di

import com.example.streetchampionproject.creating.createMatch.di.scope.CreateMatchScope
import com.example.streetchampionproject.creating.createMatch.presentation.CreateCommandMatchFragment
import com.example.streetchampionproject.creating.createMatch.presentation.CreateSingleMatchFragment
import dagger.Subcomponent

@CreateMatchScope
@Subcomponent(modules = [CreateMatchFeatureModule::class])
interface CreateMatchFeatureComponent {

    fun inject(createCommandMatchFragment: CreateCommandMatchFragment)

    fun inject(createSingleMatchFragment: CreateSingleMatchFragment)

    @Subcomponent.Builder
    interface Builder{

        fun build(): CreateMatchFeatureComponent
    }
}
