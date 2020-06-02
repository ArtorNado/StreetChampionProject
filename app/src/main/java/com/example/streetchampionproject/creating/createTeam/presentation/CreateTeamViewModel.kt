package com.example.streetchampionproject.creating.createTeam.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.common.presentation.CONSTANTS
import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import com.example.streetchampionproject.creating.createTeam.domain.interfaces.CreateTeamInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CreateTeamViewModel(
    private val createTeamInteractor: CreateTeamInteractor
) : BaseViewModel() {

    private val _status: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val status: LiveData<String> = _status

    private val _goTo: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val goTo: LiveData<String> = _goTo

    fun createTeam(teamName: String, teamCity: String) {
        _status.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE
        compositeDisposable.add(
            createTeamInteractor.createTeam(teamName, teamCity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onNotification("Команда создана")
                    _status.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                    _goTo.value = CONSTANTS.ACTION.EVENT_GO_BACK
                },
                    {error ->
                        onError(error)
                        _status.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                    })
        )
    }
}
