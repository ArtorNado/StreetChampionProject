package com.example.streetchampionproject.clubPage.presentation

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.clubPage.domain.interfaces.ClubPageInteractor
import com.example.streetchampionproject.common.domain.ERRORS
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException

class ClubPageViewModel(
    private val clubPageInteractor: ClubPageInteractor,
    private val teamId: Int
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _team by lazy { MutableLiveData<Teams>() }
    val team: LiveData<Teams> = _team

    private val _userStatus by lazy { MutableLiveData<String>() }
    val userStatus: LiveData<String> = _userStatus

    private val _pgStatus by lazy { MutableLiveData<Int>() }
    val pgStatus: LiveData<Int> = _pgStatus

    private val _error by lazy { MutableLiveData<String>() }
    val error: LiveData<String> = _error

    init {
        getUserStatus()
        getTeamData()
    }

    private fun getTeamData() {
        _pgStatus.value = View.VISIBLE
        compositeDisposable.add(
            clubPageInteractor.getTeamLocal(teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _team.value = result
                    if (result.teamName.isEmpty())
                        _pgStatus.value = View.GONE
                },
                    { error ->
                        doOnError(error)
                        _pgStatus.value = View.GONE
                    })
        )
    }

    private fun getUserStatus() {
        compositeDisposable.add(
            clubPageInteractor.getUserStatusInTeam(teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _userStatus.value = result.status
                },
                    { error ->
                        doOnError(error)
                    })
        )
    }

    fun updateUserStatus() {
        compositeDisposable.add(
            clubPageInteractor.updateUserStatusInTeam(teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                },
                    { error ->
                        doOnError(error)
                    })
        )
    }

    fun updateTeam() {
        compositeDisposable.add(
            clubPageInteractor.updateTeam(teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                },
                    { error ->
                        doOnError(error)
                    })
        )
    }

    fun applyForMembership() {
        _pgStatus.value = View.VISIBLE
        compositeDisposable.add(
            clubPageInteractor.sendNotif(teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _pgStatus.value = View.GONE
                },
                    { error ->
                        Log.e("ERROR_APPLY", error.toString())
                        doOnError(error)
                        _pgStatus.value = View.GONE
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
