package com.example.streetchampionproject.registration.di

import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.common.presentation.viewModel.ViewModelKey
import com.example.streetchampionproject.login.data.LoginRepositoryImpl
import com.example.streetchampionproject.login.data.interfaces.LoginRepository
import com.example.streetchampionproject.registration.data.RegisterRepositoryImpl
import com.example.streetchampionproject.registration.data.interfaces.RegisterRepository
import com.example.streetchampionproject.registration.di.scope.RegisterActivityScope
import com.example.streetchampionproject.registration.domain.RegisterInteractImpl
import com.example.streetchampionproject.registration.domain.interfaces.RegisterInteract
import com.example.streetchampionproject.registration.presentation.RegisterViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class RegisterFeatureModule {

    @RegisterActivityScope
    @Provides
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    fun provideRegisterViewModel(registerInteractor: RegisterInteract, navigator: Navigator): ViewModel {
        return RegisterViewModel(
            registerInteractor
        )
    }

    @RegisterActivityScope
    @Provides
    fun provideRegisterInteractor(registerInteractor: RegisterInteractImpl): RegisterInteract =
        registerInteractor

    @RegisterActivityScope
    @Provides
    fun provideRegisterRepository(registerRepositoryImpl: RegisterRepositoryImpl)
            : RegisterRepository = registerRepositoryImpl

    @RegisterActivityScope
    @Provides
    fun provideLoginRepository(loginRepository: LoginRepositoryImpl)
            : LoginRepository = loginRepository
}
