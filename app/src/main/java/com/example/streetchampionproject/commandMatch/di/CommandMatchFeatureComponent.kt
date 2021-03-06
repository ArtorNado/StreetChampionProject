package com.example.streetchampionproject.commandMatch.di

import androidx.fragment.app.Fragment
import com.example.streetchampionproject.commandMatch.di.scope.CommandMatchScope
import com.example.streetchampionproject.commandMatch.presentation.CommandMatchFragment
import dagger.BindsInstance
import dagger.Subcomponent

@CommandMatchScope
@Subcomponent(modules = [CommandMatchFeatureModule::class])
interface CommandMatchFeatureComponent {

    fun inject(commandMatchFragment: CommandMatchFragment)

    @Subcomponent.Builder
    interface Builder{

        @BindsInstance
        fun create(id: Int): Builder

        @BindsInstance
        fun withFragment(fragment: Fragment): Builder

        fun build(): CommandMatchFeatureComponent
    }
}
