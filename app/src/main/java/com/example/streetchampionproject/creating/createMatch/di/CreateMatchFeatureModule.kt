package com.example.streetchampionproject.creating.createMatch.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.common.presentation.viewModel.ViewModelKey
import com.example.streetchampionproject.creating.createMatch.data.CreateMatchRepositoryImpl
import com.example.streetchampionproject.creating.createMatch.data.interfaces.CreateMatchRepository
import com.example.streetchampionproject.creating.createMatch.di.scope.CreateMatchScope
import com.example.streetchampionproject.creating.createMatch.domain.CreateMatchInteractorImpl
import com.example.streetchampionproject.creating.createMatch.domain.interfaces.CreateMatchInteractor
import com.example.streetchampionproject.creating.createMatch.presentation.CreateMatchViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class CreateMatchFeatureModule {

    @CreateMatchScope
    @Provides
    @IntoMap
    @ViewModelKey(CreateMatchViewModel::class)
    fun provideCreateMatchViewModel(createMatchInteractor: CreateMatchInteractor): ViewModel {
        return CreateMatchViewModel(
            createMatchInteractor
        )
    }

    @CreateMatchScope
    @Provides
    fun provideViewModelCreator(
        fragment: Fragment,
        viewModelFactory: ViewModelProvider.Factory
    ): CreateMatchViewModel =
        ViewModelProvider(fragment, viewModelFactory).get(CreateMatchViewModel::class.java)

    @CreateMatchScope
    @Provides
    fun provideCreateMatchInteractor(createMatchInteractor: CreateMatchInteractorImpl): CreateMatchInteractor =
        createMatchInteractor

    @CreateMatchScope
    @Provides
    fun provideCreateMatchRepository(createMatchRepository: CreateMatchRepositoryImpl): CreateMatchRepository =
        createMatchRepository
}
