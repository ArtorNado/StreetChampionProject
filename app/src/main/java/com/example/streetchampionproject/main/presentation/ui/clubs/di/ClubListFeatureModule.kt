package com.example.streetchampionproject.main.presentation.ui.clubs.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.common.presentation.viewModel.ViewModelKey
import com.example.streetchampionproject.main.presentation.ui.clubs.data.ClubListRepositoryImpl
import com.example.streetchampionproject.main.presentation.ui.clubs.data.interfaces.ClubListRepository
import com.example.streetchampionproject.main.presentation.ui.clubs.di.scope.ClubListScope
import com.example.streetchampionproject.main.presentation.ui.clubs.domain.ClubListInteractorImpl
import com.example.streetchampionproject.main.presentation.ui.clubs.domain.interfaces.ClubListInteractor
import com.example.streetchampionproject.main.presentation.ui.clubs.presentation.ClubListViewModel
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
        clubListInteractor: ClubListInteractor,
        navigator: Navigator
    ): ViewModel {
        return ClubListViewModel(
            clubListInteractor,
            navigator
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

}
