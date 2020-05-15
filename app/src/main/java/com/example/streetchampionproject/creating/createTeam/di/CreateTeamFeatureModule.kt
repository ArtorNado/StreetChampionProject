package com.example.streetchampionproject.creating.createTeam.di

import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.common.presentation.viewModel.ViewModelKey
import com.example.streetchampionproject.creating.createTeam.data.CreateTeamRepositoryImpl
import com.example.streetchampionproject.creating.createTeam.data.interfaces.CreateTeamRepository
import com.example.streetchampionproject.creating.createTeam.di.scope.CreateTeamScope
import com.example.streetchampionproject.creating.createTeam.domain.CreateTeamInteractorImpl
import com.example.streetchampionproject.creating.createTeam.domain.interfaces.CreateTeamInteractor
import com.example.streetchampionproject.creating.createTeam.presentation.CreateTeamViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class CreateTeamFeatureModule {

    @CreateTeamScope
    @Provides
    @IntoMap
    @ViewModelKey(CreateTeamViewModel::class)
    fun provideCreateTeamViewModel(createTeamInteractor: CreateTeamInteractor): ViewModel {
        return CreateTeamViewModel(
            createTeamInteractor
        )
    }

    @CreateTeamScope
    @Provides
    fun provideCreateTeamInteractor(createTeamInteractor: CreateTeamInteractorImpl): CreateTeamInteractor =
        createTeamInteractor

    @CreateTeamScope
    @Provides
    fun provideCreateTeamRepository(createTeamRepository: CreateTeamRepositoryImpl): CreateTeamRepository =
        createTeamRepository
}
