package com.example.streetchampionproject.singleMatch.presentation.matchPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.api.scs.models.MatchSingleDetailInfo
import com.example.streetchampionproject.common.presentation.CONSTANTS
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

    private val _updateStatus: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val updateStatus: LiveData<Boolean> = _updateStatus

    private val _goTo: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val goTo: LiveData<String> = _goTo

    private val _pgStatus: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val pgStatus: LiveData<String> = _pgStatus

    init {
        getMatchData()
        getUserStatusInMatch()
    }

    private fun getMatchData() {
        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE
        compositeDisposable.add(
            singleMatchInteractor.getSingleMatch(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        _team.value = result
                        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                    },
                    { error ->
                        onError(error)
                        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                    }
                )
        )
    }

    fun updateMatchData() {
        compositeDisposable.add(
            singleMatchInteractor.updateSingleMatch(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _updateStatus.value = true
                },
                    { error ->
                        _updateStatus.value = true
                        onError(error)
                    })
        )
    }

    private fun getUserStatusInMatch() {
        compositeDisposable.add(
            singleMatchInteractor.getUserStatusInMatch(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _userStatus.value = result.status
                },
                    { error ->
                        onError(error)
                    })
        )
    }

    fun updateUserStatus() {
        compositeDisposable.add(
            singleMatchInteractor.updateUserStatusInMatch(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                },
                    { error ->
                        onError(error)
                    })
        )
    }

    fun joinInMatch() {
        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE
        compositeDisposable.add(
            singleMatchInteractor.joinInMatch(team.value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _userStatus.value = "Participant"
                    _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                },
                    { error ->
                        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                        onError(error)
                    })
        )
    }

    fun endMatch() {
        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE
        compositeDisposable.add(
            singleMatchInteractor.endSingleMatch(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _goTo.value = CONSTANTS.ACTION.EVENT_GO_BACK
                    _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                },
                    { error ->
                        onError(error)
                        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                    })
        )
    }

}
