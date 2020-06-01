package com.example.streetchampionproject.creating.createTeam.di

import androidx.fragment.app.Fragment
import com.example.streetchampionproject.creating.createTeam.di.scope.CreateTeamScope
import com.example.streetchampionproject.creating.createTeam.presentation.CreateTeamFragment
import dagger.BindsInstance
import dagger.Subcomponent

@CreateTeamScope
@Subcomponent(modules = [CreateTeamFeatureModule::class])
interface CreateTeamFeatureComponent {

    fun inject(createTeamFragment: CreateTeamFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): CreateTeamFeatureComponent

        @BindsInstance
        fun withFragment(fragment: Fragment): Builder

    }
}
