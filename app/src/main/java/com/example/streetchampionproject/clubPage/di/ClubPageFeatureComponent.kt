package com.example.streetchampionproject.clubPage.di

import androidx.fragment.app.Fragment
import com.example.streetchampionproject.clubPage.di.scope.ClubPageScope
import com.example.streetchampionproject.clubPage.presentation.ClubPageFragment
import dagger.BindsInstance
import dagger.Subcomponent

@ClubPageScope
@Subcomponent(modules = [ClubPageFeatureModule::class])
interface ClubPageFeatureComponent {

    fun inject(clubPageFragment: ClubPageFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): ClubPageFeatureComponent

        @BindsInstance
        fun create(id: Int): Builder
        
        @BindsInstance
        fun withFragment(fragment: Fragment): Builder

    }
}
