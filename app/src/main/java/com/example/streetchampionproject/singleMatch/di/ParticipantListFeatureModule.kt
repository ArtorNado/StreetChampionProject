package com.example.streetchampionproject.singleMatch.di

import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.common.presentation.viewModel.ViewModelKey
import com.example.streetchampionproject.singleMatch.data.ParticipantListRepositoryImpl
import com.example.streetchampionproject.singleMatch.data.interfaces.ParticipantListRepository
import com.example.streetchampionproject.singleMatch.di.scope.ParticipantListScope
import com.example.streetchampionproject.singleMatch.domain.ParticipantListInteractorImpl
import com.example.streetchampionproject.singleMatch.domain.interfaces.ParticipantListInteractor
import com.example.streetchampionproject.singleMatch.presentation.participantList.ParticipantListViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ParticipantListFeatureModule {

    @ParticipantListScope
    @Provides
    @IntoMap
    @ViewModelKey(ParticipantListViewModel::class)
    fun provideParticipantListViewModel(participantListInteractor: ParticipantListInteractor, matchId: Int): ViewModel {
        return ParticipantListViewModel(
            participantListInteractor,
            matchId
        )
    }

    @ParticipantListScope
    @Provides
    fun provideParticipantListInteractor(participantListInteractor: ParticipantListInteractorImpl)
            : ParticipantListInteractor =
        participantListInteractor

    @ParticipantListScope
    @Provides
    fun provideParticipantRepository(participantListRepository: ParticipantListRepositoryImpl)
            : ParticipantListRepository =
        participantListRepository
}
