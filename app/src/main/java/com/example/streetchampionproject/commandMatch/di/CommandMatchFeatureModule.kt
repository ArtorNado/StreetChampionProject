package com.example.streetchampionproject.commandMatch.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.commandMatch.data.CommandMatchRepositoryImpl
import com.example.streetchampionproject.commandMatch.data.interfaces.CommandMatchRepository
import com.example.streetchampionproject.commandMatch.di.scope.CommandMatchScope
import com.example.streetchampionproject.commandMatch.domain.CommandMatchInteractor
import com.example.streetchampionproject.commandMatch.domain.CommandMatchInteractorImpl
import com.example.streetchampionproject.commandMatch.presentation.CommandMatchViewModel
import com.example.streetchampionproject.common.presentation.viewModel.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class CommandMatchFeatureModule {

    @CommandMatchScope
    @Provides
    @IntoMap
    @ViewModelKey(CommandMatchViewModel::class)
    fun provideCommandMatchViewModel(
        commandMatchInteractor: CommandMatchInteractor,
        id: Int
    ): ViewModel {
        return CommandMatchViewModel(
            commandMatchInteractor,
            id
        )
    }

    @CommandMatchScope
    @Provides
    fun provideViewModelCreator(
        fragment: Fragment,
        viewModelFactory: ViewModelProvider.Factory
    ): CommandMatchViewModel =
        ViewModelProvider(fragment, viewModelFactory).get(CommandMatchViewModel::class.java)


    @CommandMatchScope
    @Provides
    fun provideCommandMatchInteractor(commandMatchInteractor: CommandMatchInteractorImpl): CommandMatchInteractor =
        commandMatchInteractor

    @CommandMatchScope
    @Provides
    fun provideCommandMatchRepository(commandMatchRepository: CommandMatchRepositoryImpl): CommandMatchRepository =
        commandMatchRepository

}
