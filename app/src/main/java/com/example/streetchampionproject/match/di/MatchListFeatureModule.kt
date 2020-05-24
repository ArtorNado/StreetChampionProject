package com.example.streetchampionproject.match.di

import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.common.presentation.viewModel.ViewModelKey
import com.example.streetchampionproject.match.data.MatchListRepositoryImpl
import com.example.streetchampionproject.match.data.interfaces.MatchListRepository
import com.example.streetchampionproject.match.di.scope.MatchListScope
import com.example.streetchampionproject.match.domain.MatchListInteractorImpl
import com.example.streetchampionproject.match.domain.interfaces.MatchListInteractor
import com.example.streetchampionproject.match.presentation.MatchListViewModel
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
    fun provideMatchListInteractor(matchListInteractor: MatchListInteractorImpl): MatchListInteractor =
        matchListInteractor

    @MatchListScope
    @Provides
    fun provideMatchListRepository(matchListRepository: MatchListRepositoryImpl): MatchListRepository =
        matchListRepository
}
