package com.example.streetchampionproject.main.presentation.ui.profile.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.api.scs.models.UserData
import com.example.streetchampionproject.main.presentation.ui.profile.domain.interfaces.ProfileInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ProfileViewModel(
    private val profileInteractor: ProfileInteractor
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _user by lazy { MutableLiveData<UserData>() }
    val user: LiveData<UserData> = _user

    fun getUserData(id: Int){
        val result = profileInteractor.getUserData(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                _user.value = result
            },
                { error -> Log.e("ERROR", error.toString()) })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
