package com.example.streetchampionproject.main.presentation.ui.profile.di

import com.example.streetchampionproject.main.presentation.ui.profile.presentation.ProfileFragment
import com.example.streetchampionproject.main.presentation.ui.profile.di.scope.ProfileFragmentScope
import dagger.Subcomponent

@ProfileFragmentScope
@Subcomponent(modules = [ProfileFeatureModule::class])
interface ProfileFeatureComponent {

    fun inject(profileFragment: ProfileFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): ProfileFeatureComponent

    }
}
