package com.example.streetchampionproject.registerActivity.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.registerActivity.domain.RegisterInteract
import com.example.streetchampionproject.registerActivity.scope.RegisterActivityScope

@RegisterActivityScope
class ViewModelFactory(private val registerInteractor: RegisterInteract, private val navigator: Navigator) :
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
