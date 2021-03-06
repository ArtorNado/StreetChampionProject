package com.example.streetchampionproject.clubPage.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.clubPage.domain.interfaces.ClubPageInteractor
import com.example.streetchampionproject.common.presentation.CONSTANTS
import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ClubPageViewModel(
    private val clubPageInteractor: ClubPageInteractor,
    private val teamId: Int
) : BaseViewModel() {

    private val _team by lazy { MutableLiveData<Teams>() }
    val team: LiveData<Teams> = _team

    private val _userStatus by lazy { MutableLiveData<String>() }
    val userStatus: LiveData<String> = _userStatus

    private val _pgStatus by lazy { MutableLiveData<String>() }
    val pgStatus: LiveData<String> = _pgStatus

    private val _applyButton by lazy { MutableLiveData<String>() }
    val applyButton: LiveData<String> = _applyButton


    init {
        getUserStatus()
        getTeamData()
    }

    private fun getTeamData() {
        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE
        compositeDisposable.add(
            clubPageInteractor.getTeamLocal(teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _team.value = result
                    if (result.teamName.isEmpty())
                        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                },
                    { error ->
                        onError(error)
                        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
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
                    _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                },
                    { error ->
                        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                        onError(error)
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
                        onError(error)
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
                        onError(error)
                    })
        )
    }

    fun applyForMembership() {
        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE
        compositeDisposable.add(
            clubPageInteractor.sendNotif(teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                    _applyButton.value = "gone"
                    onNotification("Заявка отправлена успешно")
                },
                    { error ->
                        onError(error)
                        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                    })

        )
    }

}
