package com.example.streetchampionproject.clubs.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.api.apiFactory.ApiFactory
import com.example.streetchampionproject.clubs.data.ClubListRepositoryImpl
import com.example.streetchampionproject.clubs.data.interfaces.ClubListRepository
import com.example.streetchampionproject.clubs.di.scope.ClubListScope
import com.example.streetchampionproject.clubs.domain.ClubListInteractorImpl
import com.example.streetchampionproject.clubs.domain.interfaces.ClubListInteractor
import com.example.streetchampionproject.clubs.presentation.ClubListViewModel
import com.example.streetchampionproject.common.presentation.viewModel.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ClubListFeatureModule {

    @ClubListScope
    @Provides
    @IntoMap
    @ViewModelKey(ClubListViewModel::class)
    fun provideClubListViewModel(
        clubListInteractor: ClubListInteractor
    ): ViewModel {
        return ClubListViewModel(
            clubListInteractor
        )
    }

    @ClubListScope
    @Provides
    fun provideViewModelCreator(
        fragment: Fragment,
        viewModelFactory: ViewModelProvider.Factory
    ): ClubListViewModel =
        ViewModelProvider(fragment, viewModelFactory).get(ClubListViewModel::class.java)

    @ClubListScope
    @Provides
    fun provideClubListInteractor(clubListInteractor: ClubListInteractorImpl): ClubListInteractor =
        clubListInteractor

    @ClubListScope
    @Provides
    fun provideClubListRepository(clubListRepository: ClubListRepositoryImpl): ClubListRepository =
        clubListRepository

    @ClubListScope
    @Provides
    fun provideService(apiFactory: ApiFactory) = apiFactory.clubsService

}
