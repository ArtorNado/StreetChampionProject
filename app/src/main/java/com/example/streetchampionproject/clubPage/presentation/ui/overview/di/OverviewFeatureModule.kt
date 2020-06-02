package com.example.streetchampionproject.clubPage.presentation.ui.overview.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.api.apiFactory.ApiFactory
import com.example.streetchampionproject.clubPage.presentation.ui.overview.data.OverviewRepositoryImpl
import com.example.streetchampionproject.clubPage.presentation.ui.overview.data.interfaces.OverviewRepository
import com.example.streetchampionproject.clubPage.presentation.ui.overview.di.scope.OverviewScope
import com.example.streetchampionproject.clubPage.presentation.ui.overview.domain.OverviewInteractorImpl
import com.example.streetchampionproject.clubPage.presentation.ui.overview.domain.interfaces.OverviewInteractor
import com.example.streetchampionproject.clubPage.presentation.ui.overview.presentation.OverviewViewModel
import com.example.streetchampionproject.common.presentation.viewModel.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class OverviewFeatureModule {

    @OverviewScope
    @Provides
    @IntoMap
    @ViewModelKey(OverviewViewModel::class)
    fun provideOverviewViewModel(overviewInteractor: OverviewInteractor, teamId: Int): ViewModel {
        return OverviewViewModel(
            overviewInteractor,
            teamId
        )
    }

    @OverviewScope
    @Provides
    fun provideViewModelCreator(
        fragment: Fragment,
        viewModelFactory: ViewModelProvider.Factory
    ): OverviewViewModel =
        ViewModelProvider(fragment, viewModelFactory).get(OverviewViewModel::class.java)


    @OverviewScope
    @Provides
    fun provideOverviewInteractor(overviewInteractor: OverviewInteractorImpl):
            OverviewInteractor = overviewInteractor

    @OverviewScope
    @Provides
    fun provideOverviewRepository(overviewRepository: OverviewRepositoryImpl):
            OverviewRepository = overviewRepository

    @OverviewScope
    @Provides
    fun provideService(apiFactory: ApiFactory) = apiFactory.overviewService
}
