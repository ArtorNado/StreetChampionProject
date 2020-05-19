package com.example.streetchampionproject.singleMatch.presentation.matchPage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.api.scs.models.MatchSingleDetailInfo
import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import com.example.streetchampionproject.singleMatch.domain.interfaces.SingleMatchInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SingleMatchViewModel(
    private val singleMatchInteractor: SingleMatchInteractor,
    private val id: Int
) : BaseViewModel() {

    private val _team: MutableLiveData<MatchSingleDetailInfo>
            by lazy { MutableLiveData<MatchSingleDetailInfo>() }

    val team: LiveData<MatchSingleDetailInfo> = _team

    private val _userStatus: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val userStatus: LiveData<String> = _userStatus

    init {
        getMatchData()
        getUserStatusInMatch()
    }

    private fun getMatchData() {
        val result =
            singleMatchInteractor.getSingleMatch(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        Log.e("RESULT_SINGLE_IND", result.toString())
                        _team.value = result
                    },
                    { error ->
                        Log.e("ERROR_SINGLE_IND", error.toString())
                    }
                )
    }

    fun updateMatchData() {
        val result = singleMatchInteractor.updateSingleMatch(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            },
                { error ->
                    Log.e("ERROR_UPDATE_IND", error.toString())
                })
    }

    private fun getUserStatusInMatch() {
        val result = singleMatchInteractor.getUserStatusInMatch(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                _userStatus.value = result.status
            },
                { error ->
                    Log.e("GEET_USR_ST", error.toString())
                })
    }

    fun updateUserStatus() {
        val result = singleMatchInteractor.updateUserStatusInMatch(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            },
                { error ->
                    Log.e("UPD_USR_ST", error.toString())
                })
    }

    fun joinInMatch() {
        val result = singleMatchInteractor.joinInMatch(team.value)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val upTeam = _team.value
                upTeam?.numberParticipant = upTeam?.numberParticipant?.plus(1)?:0
                _team.value = team.value
                _userStatus.value = "Participant"
            },
                { error ->
                    Log.e("JOIN_TEAM_ERROR", error.toString())
                })
    }
}
