package com.example.streetchampionproject.creating.createMatch.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.api.apiFactory.ApiFactory
import com.example.streetchampionproject.common.presentation.viewModel.ViewModelKey
import com.example.streetchampionproject.creating.createMatch.data.CreateMatchRepositoryImpl
import com.example.streetchampionproject.creating.createMatch.data.interfaces.CreateMatchRepository
import com.example.streetchampionproject.creating.createMatch.di.scope.CreateMatchScope
import com.example.streetchampionproject.creating.createMatch.domain.CreateMatchInteractorImpl
import com.example.streetchampionproject.creating.createMatch.domain.interfaces.CreateMatchInteractor
import com.example.streetchampionproject.creating.createMatch.presentation.commandMatch.CreateCommandMatchViewModel
import com.example.streetchampionproject.creating.createMatch.presentation.singleMatch.CreateSingleMatchViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class CreateMatchFeatureModule {

    @CreateMatchScope
    @Provides
    @IntoMap
    @ViewModelKey(CreateSingleMatchViewModel::class)
    fun provideCreateSingleMatchViewModel(createMatchInteractor: CreateMatchInteractor): ViewModel {
        return CreateSingleMatchViewModel(
            createMatchInteractor
        )
    }

    @CreateMatchScope
    @Provides
    fun provideSingleViewModelCreator(
        fragment: Fragment,
        viewModelFactory: ViewModelProvider.Factory
    ): CreateSingleMatchViewModel =
        ViewModelProvider(fragment, viewModelFactory).get(CreateSingleMatchViewModel::class.java)

    @CreateMatchScope
    @Provides
    fun provideCommandViewModelCreator(
        fragment: Fragment,
        viewModelFactory: ViewModelProvider.Factory
    ): CreateCommandMatchViewModel =
        ViewModelProvider(fragment, viewModelFactory).get(CreateCommandMatchViewModel::class.java)

    @CreateMatchScope
    @Provides
    @IntoMap
    @ViewModelKey(CreateCommandMatchViewModel::class)
    fun provideCreateCommandMatchViewModel(createMatchInteractor: CreateMatchInteractor): ViewModel {
        return CreateCommandMatchViewModel(
            createMatchInteractor
        )
    }

    @CreateMatchScope
    @Provides
    fun provideCreateMatchInteractor(createMatchInteractor: CreateMatchInteractorImpl): CreateMatchInteractor =
        createMatchInteractor

    @CreateMatchScope
    @Provides
    fun provideCreateMatchRepository(createMatchRepository: CreateMatchRepositoryImpl): CreateMatchRepository =
        createMatchRepository

    @CreateMatchScope
    @Provides
    fun provideService(apiFactory: ApiFactory) = apiFactory.createMatchService
}
