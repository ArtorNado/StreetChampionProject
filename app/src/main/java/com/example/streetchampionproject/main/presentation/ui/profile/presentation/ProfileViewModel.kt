package com.example.streetchampionproject.main.presentation.ui.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.api.scs.models.UserData
import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import com.example.streetchampionproject.main.presentation.ui.profile.domain.interfaces.ProfileInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfileViewModel(
    private val profileInteractor: ProfileInteractor,
    private val userId: Int
) : BaseViewModel() {

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
