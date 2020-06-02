package com.example.streetchampionproject.clubPage.presentation.ui.squad.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.api.apiFactory.ApiFactory
import com.example.streetchampionproject.clubPage.presentation.ui.squad.data.SquadRepositoryImpl
import com.example.streetchampionproject.clubPage.presentation.ui.squad.data.interfaces.SquadRepository
import com.example.streetchampionproject.clubPage.presentation.ui.squad.di.scope.SquadScope
import com.example.streetchampionproject.clubPage.presentation.ui.squad.domain.SquadInteractorImpl
import com.example.streetchampionproject.clubPage.presentation.ui.squad.domain.interfaces.SquadInteractor
import com.example.streetchampionproject.clubPage.presentation.ui.squad.presentation.SquadViewModel
import com.example.streetchampionproject.common.presentation.viewModel.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class SquadFeatureModule {

    @SquadScope
    @Provides
    @IntoMap
    @ViewModelKey(SquadViewModel::class)
    fun provideSquadViewModel(squadInteractor: SquadInteractor, teamId: Int): ViewModel {
        return SquadViewModel(
            squadInteractor,
            teamId
        )
    }

    @SquadScope
    @Provides
    fun provideViewModelCreator(
        fragment: Fragment,
        viewModelFactory: ViewModelProvider.Factory
    ): SquadViewModel =
        ViewModelProvider(fragment, viewModelFactory).get(SquadViewModel::class.java)


    @SquadScope
    @Provides
    fun provideSquadInteractor(squadInteractor: SquadInteractorImpl): SquadInteractor =
        squadInteractor

    @SquadScope
    @Provides
    fun provideSquadRepository(squadRepository: SquadRepositoryImpl): SquadRepository =
        squadRepository

    @SquadScope
    @Provides
    fun provideService(apiFactory: ApiFactory) = apiFactory.squadService
}
