package com.example.streetchampionproject.clubPage.presentation

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.api.scs.response.Teams
import com.example.streetchampionproject.clubPage.domain.interfaces.ClubPageInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ClubPageViewModel(
    private val clubPageInteractor: ClubPageInteractor,
    private val id: Int
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _team = MutableLiveData<Teams>()
    val team: LiveData<Teams> = _team

    private val _userStatus = MutableLiveData<String>()
    val userStatus: LiveData<String> = _userStatus

    private val _pgStatus = MutableLiveData<Int>()
    val pgStatus: LiveData<Int> = _pgStatus

    fun getData() {
        compositeDisposable.add(
            clubPageInteractor.getTeam(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _team.value = result
                    _pgStatus.value = View.GONE
                },
                    { error -> Log.e("ERROR", error.toString()) })
        )
    }

    fun determineRole(teamId: Int) {
        _pgStatus.value = View.VISIBLE
        compositeDisposable.add(
            clubPageInteractor.determineUserStatusInTeam(teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _userStatus.value = result.status
                },
                    { error -> Log.e("ERROR", error.toString()) })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
