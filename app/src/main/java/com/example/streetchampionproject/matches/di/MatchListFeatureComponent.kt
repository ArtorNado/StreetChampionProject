package com.example.streetchampionproject.matches.di

import androidx.fragment.app.Fragment
import com.example.streetchampionproject.matches.di.scope.MatchListScope
import com.example.streetchampionproject.matches.presentation.MatchListFragment
import dagger.BindsInstance
import dagger.Subcomponent

@MatchListScope
@Subcomponent(modules = [MatchListFeatureModule::class])
interface MatchListFeatureComponent {

    fun inject(matchListFragment: MatchListFragment)

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun withFragment(fragment: Fragment): Builder

        fun build(): MatchListFeatureComponent

    }
}
