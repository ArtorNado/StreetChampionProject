package com.example.streetchampionproject.clubPage.presentation.ui.squad.di

import com.example.streetchampionproject.clubPage.presentation.ui.squad.di.scope.SquadScope
import com.example.streetchampionproject.clubPage.presentation.ui.squad.presentation.SquadFragment
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
        fun create(teamId: Int): Builder

    }
}
