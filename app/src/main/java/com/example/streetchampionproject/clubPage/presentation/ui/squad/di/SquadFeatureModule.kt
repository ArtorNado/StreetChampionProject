package com.example.streetchampionproject.clubPage.presentation.ui.squad.di

import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.clubPage.presentation.ui.squad.data.SquadRepositoryImpl
import com.example.streetchampionproject.clubPage.presentation.ui.squad.data.interfaces.SquadRepository
import com.example.streetchampionproject.clubPage.presentation.ui.squad.di.scope.SquadScope
import com.example.streetchampionproject.clubPage.presentation.ui.squad.domain.SquadInteractorImpl
import com.example.streetchampionproject.clubPage.presentation.ui.squad.domain.interfaces.SquadInteractor
import com.example.streetchampionproject.clubPage.presentation.ui.squad.presentation.SquadViewModel
import com.example.streetchampionproject.common.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class SquadFeatureModule {

    @SquadScope
    @Provides
    @IntoMap
    @ViewModelKey(SquadViewModel::class)
    fun provideOverviewViewModel(squadInteractor: SquadInteractor): ViewModel {
        return SquadViewModel(
            squadInteractor
        )
    }

    @SquadScope
    @Provides
    fun provideOverviewInteractor(squadInteractor: SquadInteractorImpl): SquadInteractor = squadInteractor

    @SquadScope
    @Provides
    fun provideOverviewRepository(squadRepository: SquadRepositoryImpl): SquadRepository = squadRepository
}
