package com.example.streetchampionproject.singleMatch.di.interfaces

import com.example.streetchampionproject.singleMatch.di.SingleMatchFeatureModule
import com.example.streetchampionproject.singleMatch.di.scope.SingleMatchScope
import com.example.streetchampionproject.singleMatch.presentation.matchPage.SingleMatchFragment
import dagger.BindsInstance
import dagger.Subcomponent

@SingleMatchScope
@Subcomponent(modules = [SingleMatchFeatureModule::class])
interface SingleMatchFeatureComponent {

    fun inject(singleMatchFragment: SingleMatchFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): SingleMatchFeatureComponent

        @BindsInstance
        fun create(id: Int): Builder

    }
}
