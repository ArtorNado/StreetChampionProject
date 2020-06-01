package com.example.streetchampionproject.creating.createTeam.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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
    fun provideViewModelCreator(
        fragment: Fragment,
        viewModelFactory: ViewModelProvider.Factory
    ): CreateTeamViewModel =
        ViewModelProvider(fragment, viewModelFactory).get(CreateTeamViewModel::class.java)

    @CreateTeamScope
    @Provides
    fun provideCreateTeamInteractor(createTeamInteractor: CreateTeamInteractorImpl): CreateTeamInteractor =
        createTeamInteractor

    @CreateTeamScope
    @Provides
    fun provideCreateTeamRepository(createTeamRepository: CreateTeamRepositoryImpl): CreateTeamRepository =
        createTeamRepository
}
