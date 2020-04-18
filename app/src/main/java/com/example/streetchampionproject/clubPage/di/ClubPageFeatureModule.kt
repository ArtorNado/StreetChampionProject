package com.example.streetchampionproject.clubPage.di

import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.clubPage.data.ClubPageRepositoryImpl
import com.example.streetchampionproject.clubPage.data.interfaces.ClubPageRepository
import com.example.streetchampionproject.clubPage.di.scope.ClubPageScope
import com.example.streetchampionproject.clubPage.domain.ClubPageInteractorImpl
import com.example.streetchampionproject.clubPage.domain.interfaces.ClubPageInteractor
import com.example.streetchampionproject.clubPage.presentation.ClubPageViewModel
import com.example.streetchampionproject.common.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ClubPageFeatureModule {

    @ClubPageScope
    @Provides
    @IntoMap
    @ViewModelKey(ClubPageViewModel::class)
    fun provideClubPageViewModel(clubPageInteractor: ClubPageInteractor): ViewModel {
        return ClubPageViewModel(
            clubPageInteractor
        )
    }

    @ClubPageScope
    @Provides
    fun provideClubPageInteractor(clubPageInteractor: ClubPageInteractorImpl): ClubPageInteractor = clubPageInteractor

    @ClubPageScope
    @Provides
    fun provideClubPageRepository(clubPageRepository: ClubPageRepositoryImpl): ClubPageRepository = clubPageRepository
}

