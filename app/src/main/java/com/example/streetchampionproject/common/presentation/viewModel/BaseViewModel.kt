package com.example.streetchampionproject.common.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.common.domain.Exceptions
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    val errorLiveData = MutableLiveData<Int>()
    val notificationLiveData = MutableLiveData<String>()

    protected val compositeDisposable = CompositeDisposable()

    fun onError(throwable: Throwable) {
        if (throwable is Exceptions) {
            when (throwable.kind) {
                Exceptions.Kind.BUSINESS -> {
                    errorLiveData.value = throwable.errorResponseCode?.stringResource
                }
            }
        }
    }

    fun onNotification(text: String){
        notificationLiveData.value = text
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }
}
