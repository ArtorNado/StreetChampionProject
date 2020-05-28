package com.example.streetchampionproject.clubPage.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.clubPage.data.ClubPageRepositoryImpl
import com.example.streetchampionproject.clubPage.data.interfaces.ClubPageRepository
import com.example.streetchampionproject.clubPage.di.scope.ClubPageScope
import com.example.streetchampionproject.clubPage.domain.ClubPageInteractorImpl
import com.example.streetchampionproject.clubPage.domain.interfaces.ClubPageInteractor
import com.example.streetchampionproject.clubPage.presentation.ClubPageViewModel
import com.example.streetchampionproject.common.presentation.viewModel.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ClubPageFeatureModule {

    @ClubPageScope
    @Provides
    @IntoMap
    @ViewModelKey(ClubPageViewModel::class)
    fun provideClubPageViewModel(clubPageInteractor: ClubPageInteractor, id: Int): ViewModel {
        return ClubPageViewModel(
            clubPageInteractor,
            id
        )
    }

    @ClubPageScope
    @Provides
    fun provideViewModelCreator(
        fragment: Fragment,
        viewModelFactory: ViewModelProvider.Factory
    ): ClubPageViewModel =
        ViewModelProvider(fragment, viewModelFactory).get(ClubPageViewModel::class.java)


    @ClubPageScope
    @Provides
    fun provideClubPageInteractor(clubPageInteractor: ClubPageInteractorImpl): ClubPageInteractor =
        clubPageInteractor

    @ClubPageScope
    @Provides
    fun provideClubPageRepository(clubPageRepository: ClubPageRepositoryImpl): ClubPageRepository =
        clubPageRepository
}

