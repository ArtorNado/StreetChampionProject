package com.example.streetchampionproject.login.presentation

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.constants.ERRORS
import com.example.streetchampionproject.login.domain.interfaces.LoginInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(
    private val loginInteractor: LoginInteractor,
    private val navigator: Navigator
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _pgStatus = MutableLiveData<Int>()
    val pgStatus: LiveData<Int> = _pgStatus

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun clickLogin(email: String, password: String, context: Context) {
        _pgStatus.value = View.VISIBLE
        compositeDisposable.add(loginInteractor.logIn(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                loginInteractor.writeInStorage("AuthToken", result.token)
                getUserId(email, context)
                _pgStatus.value = View.GONE
            },
                { error ->
                    errorMessage(error)
                    _pgStatus.value = View.GONE
                }))
    }

    fun clickRegistr(context: Context) {
        navigator.openRegister(context)
    }

    private fun getUserId(userLogin: String, context: Context){
        val response = loginInteractor.userId(userLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                navigator.openMain(context, result.userId)
                writeInStorage("userId", result.userId.toString())
                _pgStatus.value = View.GONE
            },
                { error ->
                    errorMessage(error)
                    _pgStatus.value = View.GONE
                })
    }

    private fun writeInStorage(name: String, value: String) = loginInteractor.writeInStorage(name, value)

    private fun errorMessage(error: Throwable){
        when(error.localizedMessage.toString()){
            ERRORS.NAME.HTTP_500 -> {
                _error.value = ERRORS.MESSAGE.WRONG_LOG_PAS
            }
        }
    }
}
