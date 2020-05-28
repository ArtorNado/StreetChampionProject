package com.example.streetchampionproject.clubPage.presentation.ui.overview.di

import androidx.fragment.app.Fragment
import com.example.streetchampionproject.clubPage.presentation.ui.overview.di.scope.OverviewScope
import com.example.streetchampionproject.clubPage.presentation.ui.overview.presentation.OverviewFragment
import dagger.BindsInstance
import dagger.Subcomponent

@OverviewScope
@Subcomponent(modules = [OverviewFeatureModule::class])
interface OverviewFeatureComponent {

    fun inject(overviewFragment: OverviewFragment)

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun withFragment(fragment: Fragment): Builder

        @BindsInstance
        fun create(teamId: Int): Builder

        fun build(): OverviewFeatureComponent

    }
}
