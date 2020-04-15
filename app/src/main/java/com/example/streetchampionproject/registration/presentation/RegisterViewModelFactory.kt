package com.example.streetchampionproject.registration.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.registration.domain.RegisterInteractImpl
import com.example.streetchampionproject.registration.di.scope.RegisterActivityScope

@RegisterActivityScope
class RegisterViewModelFactory(private val registerInteractor: RegisterInteractImpl, private val navigator: Navigator) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T?>): T {
        return if (modelClass == RegisterViewModel::class.java) {
            RegisterViewModel(
                registerInteractor,
                navigator
            ) as T
        } else {
            throw IllegalArgumentException("Invalid view model type")
        }
    }

}
