package com.example.streetchampionproject.matches.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.api.apiFactory.ApiFactory
import com.example.streetchampionproject.common.presentation.viewModel.ViewModelKey
import com.example.streetchampionproject.matches.data.MatchListRepositoryImpl
import com.example.streetchampionproject.matches.data.interfaces.MatchListRepository
import com.example.streetchampionproject.matches.di.scope.MatchListScope
import com.example.streetchampionproject.matches.domain.MatchListInteractorImpl
import com.example.streetchampionproject.matches.domain.interfaces.MatchListInteractor
import com.example.streetchampionproject.matches.presentation.MatchListViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class MatchListFeatureModule {

    @MatchListScope
    @Provides
    @IntoMap
    @ViewModelKey(MatchListViewModel::class)
    fun provideMatchListViewModel(matchListInteractor: MatchListInteractor): ViewModel {
        return MatchListViewModel(
            matchListInteractor
        )
    }

    @MatchListScope
    @Provides
    fun provideViewModelCreator(
        fragment: Fragment,
        viewModelFactory: ViewModelProvider.Factory
    ): MatchListViewModel =
        ViewModelProvider(fragment, viewModelFactory).get(MatchListViewModel::class.java)


    @MatchListScope
    @Provides
    fun provideMatchListInteractor(matchListInteractor: MatchListInteractorImpl): MatchListInteractor =
        matchListInteractor

    @MatchListScope
    @Provides
    fun provideMatchListRepository(matchListRepository: MatchListRepositoryImpl): MatchListRepository =
        matchListRepository

    @MatchListScope
    @Provides
    fun provideService(apiFactory: ApiFactory) = apiFactory.matchListService
}
