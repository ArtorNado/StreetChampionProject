package com.example.streetchampionproject.matchHistory.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.api.apiFactory.ApiFactory
import com.example.streetchampionproject.common.presentation.viewModel.ViewModelKey
import com.example.streetchampionproject.matchHistory.data.MatchHistoryRepositoryImpl
import com.example.streetchampionproject.matchHistory.data.interfaces.MatchHistoryRepository
import com.example.streetchampionproject.matchHistory.di.scope.MatchHistoryScope
import com.example.streetchampionproject.matchHistory.domain.MatchHistoryInteractorImpl
import com.example.streetchampionproject.matchHistory.domain.interfaces.MatchHistoryInteractor
import com.example.streetchampionproject.matchHistory.presentation.MatchHistoryViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class MatchHistoryFeatureModule {

    @MatchHistoryScope
    @Provides
    @IntoMap
    @ViewModelKey(MatchHistoryViewModel::class)
    fun provideOverviewViewModel(matchHistoryInteractor: MatchHistoryInteractor, teamId: Int): ViewModel {
        return MatchHistoryViewModel(
            matchHistoryInteractor,
            teamId
        )
    }

    @MatchHistoryScope
    @Provides
    fun provideViewModelCreator(
        fragment: Fragment,
        viewModelFactory: ViewModelProvider.Factory
    ): MatchHistoryViewModel =
        ViewModelProvider(fragment, viewModelFactory).get(MatchHistoryViewModel::class.java)


    @MatchHistoryScope
    @Provides
    fun provideOverviewInteractor(overviewInteractor: MatchHistoryInteractorImpl):
            MatchHistoryInteractor = overviewInteractor

    @MatchHistoryScope
    @Provides
    fun provideOverviewRepository(overviewRepository: MatchHistoryRepositoryImpl):
            MatchHistoryRepository = overviewRepository

    @MatchHistoryScope
    @Provides
    fun provideService(apiFactory: ApiFactory) = apiFactory.matchHistoryService
}
