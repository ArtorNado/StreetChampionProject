package com.example.streetchampionproject.registration.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.common.domain.Exceptions
import com.example.streetchampionproject.common.presentation.CONSTANTS
import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import com.example.streetchampionproject.registration.data.model.User
import com.example.streetchampionproject.registration.domain.interfaces.RegisterInteract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RegisterViewModel(
    private val registerInteractor: RegisterInteract
) : BaseViewModel() {

    private val _pgStatus by lazy { MutableLiveData<String>() }
    val pgStatus: LiveData<String> = _pgStatus

    private val _error by lazy { MutableLiveData<Int>() }
    val error: LiveData<Int> = _error

    private val _goTo by lazy { MutableLiveData<String>() }
    val goTo: LiveData<String> = _goTo

    var userId = 0

    fun clickRegister(u: User) {
        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE
        compositeDisposable.add(
            registerInteractor.registration(u)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    userId = result.userId
                    _goTo.value = CONSTANTS.ACTION.EVENT_GO_MAIN
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
