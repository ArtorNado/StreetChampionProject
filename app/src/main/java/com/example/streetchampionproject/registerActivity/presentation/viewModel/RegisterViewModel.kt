package com.example.streetchampionproject.registerActivity.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.registerActivity.data.model.User
import com.example.streetchampionproject.registerActivity.domain.RegisterInteractImpl
import com.example.streetchampionproject.registerActivity.domain.interfaces.RegisterInteract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RegisterViewModel(
    private val registerInteractor: RegisterInteract,
    private val navigator: Navigator
): ViewModel() {

    fun clickRegister(u: User){
        registerInteractor.registration(u)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> Log.e("RESULT", result.toString()) },
                { error -> Log.e("ERROR", error.toString()) })
    }
}
