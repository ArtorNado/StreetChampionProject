package com.example.streetchampionproject.profile.di

import androidx.fragment.app.Fragment
import com.example.streetchampionproject.profile.di.scope.ProfileFragmentScope
import com.example.streetchampionproject.profile.presentation.ProfileFragment
import dagger.BindsInstance
import dagger.Subcomponent

@ProfileFragmentScope
@Subcomponent(modules = [ProfileFeatureModule::class])
interface ProfileFeatureComponent {

    fun inject(profileFragment: ProfileFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): ProfileFeatureComponent

        @BindsInstance
        fun create(userId: Int): Builder

        @BindsInstance
        fun withFragment(fragment: Fragment): Builder

    }
}
