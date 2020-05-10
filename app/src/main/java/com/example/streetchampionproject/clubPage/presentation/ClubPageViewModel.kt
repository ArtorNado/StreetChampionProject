package com.example.streetchampionproject.clubPage.presentation

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.clubPage.domain.interfaces.ClubPageInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

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

    fun getData() {
        _pgStatus.value = View.VISIBLE
        compositeDisposable.add(
            clubPageInteractor.getTeam(teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _team.value = result
                    _pgStatus.value = View.GONE
                },
                    { error ->
                        Log.e("ERROR", error.toString())
                        _pgStatus.value = View.GONE
                    })
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
                    _pgStatus.value = View.GONE
                },
                    { error ->
                        Log.e("ERROR", error.toString())
                        _pgStatus.value = View.GONE
                    })
        )
    }

    fun applyForMembership() {
        _pgStatus.value = View.VISIBLE
        compositeDisposable.add(
            clubPageInteractor.sendNotif(teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    Log.e("RESULT_APPLY_MEMBER", result.toString())
                    _pgStatus.value = View.GONE
                },
                    { error ->
                        Log.e("ERROR_apply", error.toString())
                        _pgStatus.value = View.GONE
                    })

        )
    }

    /*private fun errorMessage(error: Throwable){
        when(error.localizedMessage.toString()){
            ERRORS.NAME.HTTP_403 -> {
                _error.value = ERRORS.MESSAGE.ALREADY_IN_TEAM
            }
        }
    }*/

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
