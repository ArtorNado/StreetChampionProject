package com.example.streetchampionproject.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.common.domain.Exceptions
import com.example.streetchampionproject.common.presentation.CONSTANTS
import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import com.example.streetchampionproject.login.domain.interfaces.LoginInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel(
    private val loginInteractor: LoginInteractor
) : BaseViewModel() {

    private val _pgStatus by lazy { MutableLiveData<String>() }
    val pgStatus: LiveData<String> = _pgStatus

    private val _error by lazy { MutableLiveData<Int>() }
    val error: LiveData<Int> = _error

    private val _goTo by lazy { MutableLiveData<String>() }
    val goTo: LiveData<String> = _goTo

    var userId = 0


    fun clickLogin(email: String, password: String) {
        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE
        compositeDisposable.add(
            loginInteractor.logIn(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    userId = it.userId
                    _goTo.value = "Go to main"
                    _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                },
                    { error ->
                        errorMessage(error)
                        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                    })
        )
    }

    private fun errorMessage(throwable: Throwable) {
        if (throwable is Exceptions) {
            when (throwable.kind) {
                Exceptions.Kind.BUSINESS -> {
                    _error.value = throwable.errorResponseCode?.stringResource
                }
            }
        }
    }

}
