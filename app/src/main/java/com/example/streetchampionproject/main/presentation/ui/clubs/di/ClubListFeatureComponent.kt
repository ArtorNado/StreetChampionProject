package com.example.streetchampionproject.main.presentation.ui.clubs.di

import com.example.streetchampionproject.main.presentation.ui.clubs.di.scope.ClubListScope
import com.example.streetchampionproject.main.presentation.ui.clubs.presentation.ClubListFragment
import dagger.Subcomponent

@ClubListScope
@Subcomponent(modules = [ClubListFeatureModule::class])
interface ClubListFeatureComponent {

    fun inject(clubListFragment: ClubListFragment)

    @Subcomponent.Builder
    interface Builder{

        fun build(): ClubListFeatureComponent
    }
}
