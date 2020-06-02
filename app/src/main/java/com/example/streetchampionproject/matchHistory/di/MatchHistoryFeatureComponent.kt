package com.example.streetchampionproject.matchHistory.di

import androidx.fragment.app.Fragment
import com.example.streetchampionproject.matchHistory.di.scope.MatchHistoryScope
import com.example.streetchampionproject.matchHistory.presentation.MatchHistoryFragment
import dagger.BindsInstance
import dagger.Subcomponent

@MatchHistoryScope
@Subcomponent(modules = [MatchHistoryFeatureModule::class])
interface MatchHistoryFeatureComponent {

    fun inject(overviewFragment: MatchHistoryFragment)

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun withFragment(fragment: Fragment): Builder

        @BindsInstance
        fun create(teamId: Int): Builder

        fun build(): MatchHistoryFeatureComponent

    }
}
