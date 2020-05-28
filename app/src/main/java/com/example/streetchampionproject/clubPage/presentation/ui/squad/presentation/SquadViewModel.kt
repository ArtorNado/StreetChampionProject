package com.example.streetchampionproject.clubPage.presentation.ui.squad.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.api.scs.models.Players
import com.example.streetchampionproject.clubPage.presentation.ui.squad.domain.interfaces.SquadInteractor
import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SquadViewModel(
    private val squadInteractor: SquadInteractor,
    private val teamId: Int
) : BaseViewModel() {

    private val _players by lazy { MutableLiveData<List<Players>>() }
    val players: LiveData<List<Players>> = _players

    private val _error by lazy { MutableLiveData<String>() }
    val error: LiveData<String> = _error

    init {
        getPlayers()
    }

    private fun getPlayers() {
        compositeDisposable.add(
            squadInteractor.getPlayers(teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _players.value = result
                },
                    { error ->
                        onError(error)
                    })
        )
    }

    fun updatePlayers() {
        compositeDisposable.add(
            squadInteractor.updatePlayers(teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                },
                    { error ->
                        onError(error)
                    })
        )
    }

}
