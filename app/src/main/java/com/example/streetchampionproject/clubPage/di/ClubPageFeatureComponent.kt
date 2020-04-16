package com.example.streetchampionproject.clubPage.di

import com.example.streetchampionproject.clubPage.di.scope.ClubPageScope
import com.example.streetchampionproject.clubPage.presentation.ClubPageFragment
import dagger.Subcomponent

@ClubPageScope
@Subcomponent(modules = [ClubPageFeatureModule::class])
interface ClubPageFeatureComponent {

    fun inject(clubPageFragment: ClubPageFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): ClubPageFeatureComponent

    }
}
