package com.example.streetchampionproject.singleMatch.di

import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.common.presentation.viewModel.ViewModelKey
import com.example.streetchampionproject.singleMatch.data.SingleMatchRepositoryImpl
import com.example.streetchampionproject.singleMatch.data.interfaces.SingleMatchRepository
import com.example.streetchampionproject.singleMatch.di.scope.SingleMatchScope
import com.example.streetchampionproject.singleMatch.domain.SingleMatchInteractorImpl
import com.example.streetchampionproject.singleMatch.domain.interfaces.SingleMatchInteractor
import com.example.streetchampionproject.singleMatch.presentation.matchPage.SingleMatchViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class SingleMatchFeatureModule {

    @SingleMatchScope
    @Provides
    @IntoMap
    @ViewModelKey(SingleMatchViewModel::class)
    fun provideSingleMatchViewModel(singleMatchInteractor: SingleMatchInteractor, id: Int): ViewModel {
        return SingleMatchViewModel(
            singleMatchInteractor,
            id
        )
    }

    @SingleMatchScope
    @Provides
    fun provideSingleMatchInteractor(singleMatchInteractor: SingleMatchInteractorImpl): SingleMatchInteractor =
        singleMatchInteractor

    @SingleMatchScope
    @Provides
    fun provideSingleMatchRepository(singleMatchRepository: SingleMatchRepositoryImpl): SingleMatchRepository =
        singleMatchRepository
}
