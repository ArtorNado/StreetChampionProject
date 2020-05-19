package com.example.streetchampionproject.match.di

import com.example.streetchampionproject.match.di.scope.MatchListScope
import com.example.streetchampionproject.match.presentation.MatchListFragment
import dagger.Subcomponent

@MatchListScope
@Subcomponent(modules = [MatchListFeatureModule::class])
interface MatchListFeatureComponent {

    fun inject(matchListFragment: MatchListFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): MatchListFeatureComponent

    }
}
