package com.example.streetchampionproject.clubs.di

import androidx.fragment.app.Fragment
import com.example.streetchampionproject.clubs.di.scope.ClubListScope
import com.example.streetchampionproject.clubs.presentation.ClubListFragment
import dagger.BindsInstance
import dagger.Subcomponent

@ClubListScope
@Subcomponent(modules = [ClubListFeatureModule::class])
interface ClubListFeatureComponent {

    fun inject(clubListFragment: ClubListFragment)

    @Subcomponent.Builder
    interface Builder{

        @BindsInstance
        fun withFragment(fragment: Fragment): Builder

        fun build(): ClubListFeatureComponent
    }
}
