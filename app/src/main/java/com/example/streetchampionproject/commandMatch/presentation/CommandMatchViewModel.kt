package com.example.streetchampionproject.commandMatch.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.api.scs.models.MatchCommand
import com.example.streetchampionproject.api.scs.models.UserTeamRole
import com.example.streetchampionproject.commandMatch.domain.CommandMatchInteractor
import com.example.streetchampionproject.common.presentation.CONSTANTS
import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CommandMatchViewModel(
    private val commandMatchInteractor: CommandMatchInteractor,
    private val id: Int
) : BaseViewModel() {

    private val _match: MutableLiveData<MatchCommand> by lazy { MutableLiveData<MatchCommand>() }
    val match: LiveData<MatchCommand> = _match

    private val _userStatus: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val userStatus: LiveData<String> = _userStatus

    private val _events: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val events: LiveData<String> = _events

    private val _pgStatus: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val pgStatus: LiveData<String> = _pgStatus


    init {
        getCommandMatch()
        determineUserStatus()
    }

    private fun determineUserStatus() {
        compositeDisposable.add(
            commandMatchInteractor.determineUserStatus()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    setUserStatus(result)
                },
                    { error ->
                        onError(error)
                    })
        )

    }

    private fun getCommandMatch() {
        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE
        compositeDisposable.add(
            commandMatchInteractor.getCommandMatch(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _match.value = result
                    _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                },
                    { error ->
                        onError(error)
                        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                    })
        )
    }

    fun updateCommandMatch() {
        compositeDisposable.add(
            commandMatchInteractor.updateCommandMatch(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                },
                    { error ->
                        onError(error)
                    })
        )
    }


    private fun setUserStatus(userTeamRole: UserTeamRole) {
        _userStatus.value = when (userTeamRole.status) {
            "Admin" -> {
                if (match.value?.firstTeamId == userTeamRole.teamId) "Admin match"
                else if (match.value?.secondTeamId == userTeamRole.teamId) "Participant"
                else if(match.value?.secondTeamId == 0) "Admin another team"
                else "Undefined"
            }
            else -> "Undefined"
        }
    }

    fun endCommandMatch(firstTeamScore: Int, secondTeamScore: Int) {
        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE
        compositeDisposable.add(
            commandMatchInteractor.endCommandMatch(id, firstTeamScore, secondTeamScore)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _events.value = CONSTANTS.ACTION.EVENT_GO_BACK
                    _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                },
                    { error ->
                        onError(error)
                        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                    })
        )
    }

    fun joinCommandMatch() {
        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE
        compositeDisposable.add(
            commandMatchInteractor.joinCommandMatch(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _events.value = CONSTANTS.ACTION.EVENT_GO_BACK
                    _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                },
                    { error ->
                        onError(error)
                        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                    })
        )
    }
}
