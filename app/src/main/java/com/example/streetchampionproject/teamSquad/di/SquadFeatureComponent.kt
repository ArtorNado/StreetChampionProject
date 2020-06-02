package com.example.streetchampionproject.teamSquad.di

import androidx.fragment.app.Fragment
import com.example.streetchampionproject.teamSquad.di.scope.SquadScope
import com.example.streetchampionproject.teamSquad.presentation.SquadFragment
import dagger.BindsInstance
import dagger.Subcomponent

@SquadScope
@Subcomponent(modules = [SquadFeatureModule::class])
interface SquadFeatureComponent {

    fun inject(squadFragment: SquadFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): SquadFeatureComponent

        @BindsInstance
        fun withFragment(fragment: Fragment): Builder

        @BindsInstance
        fun create(teamId: Int): Builder

    }
}
