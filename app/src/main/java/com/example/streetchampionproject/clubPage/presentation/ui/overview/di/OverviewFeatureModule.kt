package com.example.streetchampionproject.clubPage.presentation.ui.overview.di

import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.clubPage.presentation.ui.overview.data.OverviewRepositoryImpl
import com.example.streetchampionproject.clubPage.presentation.ui.overview.data.interfaces.OverviewRepository
import com.example.streetchampionproject.clubPage.presentation.ui.overview.di.scope.OverviewScope
import com.example.streetchampionproject.clubPage.presentation.ui.overview.domain.OverviewInteractorImpl
import com.example.streetchampionproject.clubPage.presentation.ui.overview.domain.interfaces.OverviewInteractor
import com.example.streetchampionproject.clubPage.presentation.ui.overview.presentation.OverviewViewModel
import com.example.streetchampionproject.common.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class OverviewFeatureModule {

    @OverviewScope
    @Provides
    @IntoMap
    @ViewModelKey(OverviewViewModel::class)
    fun provideOverviewViewModel(overviewInteractor: OverviewInteractor): ViewModel {
        return OverviewViewModel(
            overviewInteractor
        )
    }

    @OverviewScope
    @Provides
    fun provideOverviewInteractor(overviewInteractor: OverviewInteractorImpl): OverviewInteractor = overviewInteractor

    @OverviewScope
    @Provides
    fun provideOverviewRepository(overviewRepository: OverviewRepositoryImpl): OverviewRepository = overviewRepository
}
