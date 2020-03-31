package com.example.streetchampionproject.registerActivity.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.registerActivity.data.model.User
import com.example.streetchampionproject.registerActivity.domain.RegisterInteract

class RegisterViewModel(
    private val registerInteractor: RegisterInteract,
    private val navigator: Navigator
): ViewModel() {

    fun clickRegister(u: User){
        registerInteractor.registration(u)
    }
}
