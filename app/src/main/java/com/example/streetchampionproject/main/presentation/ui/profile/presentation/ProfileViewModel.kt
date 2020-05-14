package com.example.streetchampionproject.main.presentation.ui.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.api.scs.models.UserData
import com.example.streetchampionproject.common.domain.ERRORS
import com.example.streetchampionproject.main.presentation.ui.profile.domain.interfaces.ProfileInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException

class ProfileViewModel(
    private val profileInteractor: ProfileInteractor,
    private val userId: Int
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _user by lazy { MutableLiveData<UserData>() }
    val user: LiveData<UserData> = _user

    private val _error by lazy { MutableLiveData<String>() }
    val error: LiveData<String> = _error

    init {
        getUserData()
        updateUserData()
    }

    private fun getUserData() {
        compositeDisposable.add(
            profileInteractor.getUserData(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _user.value = result
                },
                    { error ->
                        doOnError(error)
                    })
        )
    }

    private fun updateUserData() {
        compositeDisposable.add(
            profileInteractor.updateUserData(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                },
                    { error ->
                        doOnError(error)
                    })
        )
    }

    private fun doOnError(throwable: Throwable) {
        if (throwable is UnknownHostException) _error.value = ERRORS.MESSAGE.NETWORK_EXCEPTION
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
