package com.example.streetchampionproject.common.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        Log.e("On_CLEARED", "CLEAR")
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }
}
