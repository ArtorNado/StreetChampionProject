package com.example.streetchampionproject.singleMatch.di.interfaces

import com.example.streetchampionproject.singleMatch.di.ParticipantListFeatureModule
import com.example.streetchampionproject.singleMatch.di.scope.ParticipantListScope
import com.example.streetchampionproject.singleMatch.presentation.participantList.ParticipantListFragment
import dagger.BindsInstance
import dagger.Subcomponent

@ParticipantListScope
@Subcomponent(modules = [ParticipantListFeatureModule::class])
interface ParticipantListFeatureComponent {

    fun inject(participantListFragment: ParticipantListFragment)

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun create(matchId: Int): Builder

        fun build(): ParticipantListFeatureComponent
    }
}
