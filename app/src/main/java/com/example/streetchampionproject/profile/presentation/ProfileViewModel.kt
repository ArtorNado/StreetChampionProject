package com.example.streetchampionproject.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.api.scs.models.UserData
import com.example.streetchampionproject.common.presentation.CONSTANTS
import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import com.example.streetchampionproject.profile.domain.interfaces.ProfileInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfileViewModel(
    private val profileInteractor: ProfileInteractor,
    private val userId: Int
) : BaseViewModel() {

    private val _user by lazy { MutableLiveData<UserData>() }
    val user: LiveData<UserData> = _user

    private val _pgStatus by lazy { MutableLiveData<String>() }
    val pgStatus: LiveData<String> = _pgStatus

    init {
        getUserData()
        updateUserData()
    }

    private fun getUserData() {
        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE
        compositeDisposable.add(
            profileInteractor.getUserData(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                    _user.value = result
                },
                    { error ->
                        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                        onError(error)
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
                        onError(error)
                    })
        )
    }

}
