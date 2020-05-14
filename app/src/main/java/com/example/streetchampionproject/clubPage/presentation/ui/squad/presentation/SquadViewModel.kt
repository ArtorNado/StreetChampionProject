package com.example.streetchampionproject.clubPage.presentation.ui.squad.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.api.scs.models.Players
import com.example.streetchampionproject.clubPage.presentation.ui.squad.domain.interfaces.SquadInteractor
import com.example.streetchampionproject.common.domain.ERRORS
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException

class SquadViewModel(
    private val squadInteractor: SquadInteractor,
    private val teamId: Int
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _players by lazy { MutableLiveData<List<Players>>() }
    val players: LiveData<List<Players>> = _players

    private val _error by lazy { MutableLiveData<String>() }
    val error: LiveData<String> = _error

    init {
        getPlayers()
        updatePlayers()
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
                        doOnError(error)
                    })
        )
    }

    private fun updatePlayers() {
        compositeDisposable.add(
            squadInteractor.updatePlayers(teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                },
                    { error ->
                        doOnError(error)
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
