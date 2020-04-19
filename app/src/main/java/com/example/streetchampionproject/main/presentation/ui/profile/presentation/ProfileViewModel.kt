package com.example.streetchampionproject.main.presentation.ui.profile.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.main.presentation.ui.profile.domain.interfaces.ProfileInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfileViewModel(
    private val profileInteractor: ProfileInteractor
) : ViewModel() {

    private val _userLogin = MutableLiveData<String>()
    val userLogin: LiveData<String> = _userLogin

    private val _userFirstName = MutableLiveData<String>()
    val userFirstName: LiveData<String> = _userFirstName

    private val _userSecondName = MutableLiveData<String>()
    val userSecondname: LiveData<String> = _userSecondName

    private val _userGender = MutableLiveData<String>()
    val userGender: LiveData<String> = _userGender

    private val _userCity = MutableLiveData<String>()
    val userCity: LiveData<String> = _userCity


    fun getUserData(id: Int){
        val result = profileInteractor.getUserData(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> Log.e("RESULT", result.toString())
                _userLogin.value = result.userFirstName
                _userFirstName.value = result.userFirstName
                _userSecondName.value = result.userSecondName
                _userGender.value = result.userGender
                _userCity.value = result.userCity
            },
                { error -> Log.e("ERROR", error.toString()) })
    }
}
