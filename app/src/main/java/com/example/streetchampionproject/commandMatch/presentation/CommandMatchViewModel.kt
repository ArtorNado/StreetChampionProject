package com.example.streetchampionproject.commandMatch.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.api.scs.models.MatchCommand
import com.example.streetchampionproject.api.scs.models.UserTeamRole
import com.example.streetchampionproject.commandMatch.domain.CommandMatchInteractor
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

    init {
        getCommandMatch()
    }

    private fun determineUserStatus() {
        compositeDisposable.add(
            commandMatchInteractor.determineUserStatus()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    setUserStatus(result)
                },
                    {
                        Log.e("GET_STATUS_M_ERROR", it.toString())
                    })
        )

    }

    private fun getCommandMatch() {
        compositeDisposable.add(
            commandMatchInteractor.getCommandMatch(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _match.value = result
                    determineUserStatus()
                },
                    {
                        Log.e("GET_COM_M_ERROR", it.toString())
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
                        Log.e("ERROR_UPDATE_CMD", error.toString())
                    })
        )
    }


    private fun setUserStatus(userTeamRole: UserTeamRole) {
        _userStatus.value = when (userTeamRole.status) {
            "Admin" -> {
                if (match.value?.firstTeamId == userTeamRole.teamId) "Admin match"
                else if (match.value?.secondTeamId == userTeamRole.teamId) "Participant"
                else "Admin another team"
            }
            else -> "Undefined"
        }
    }

    fun endCommandMatch(firstTeamScore: Int, secondTeamScore: Int) {
        compositeDisposable.add(
            commandMatchInteractor.endCommandMatch(id, firstTeamScore, secondTeamScore)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _events.value = EVENT_GO_BACK
                },
                    { error ->
                        Log.e("ERROR_UPDATE", error.toString())
                    })
        )
    }

    fun joinCommandMatch() {
        compositeDisposable.add(
            commandMatchInteractor.joinCommandMatch(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _events.value = EVENT_GO_BACK
                },
                    { error ->
                        Log.e("ERROR_UPDATE_CMD", error.toString())
                    })
        )
    }

    companion object {
        private const val EVENT_GO_BACK = "Go back"
    }
}
