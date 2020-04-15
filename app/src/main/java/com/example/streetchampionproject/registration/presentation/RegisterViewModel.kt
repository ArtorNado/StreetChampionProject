package com.example.streetchampionproject.registration.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.registration.data.model.User
import com.example.streetchampionproject.registration.domain.interfaces.RegisterInteract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RegisterViewModel(
    private val registerInteractor: RegisterInteract,
    private val navigator: Navigator
): ViewModel() {

    fun clickRegister(u: User, context: Context){
        val desponce = registerInteractor.registration(u)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.e("RESULT", result.toString())
                navigator.openLogin(context)

            },
                { error -> Log.e("ERROR", error.toString()) })
    }

}
