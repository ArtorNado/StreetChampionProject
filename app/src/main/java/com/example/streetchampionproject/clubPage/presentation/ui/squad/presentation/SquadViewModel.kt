package com.example.streetchampionproject.clubPage.presentation.ui.squad.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.api.scs.models.Players
import com.example.streetchampionproject.clubPage.presentation.ui.squad.domain.interfaces.SquadInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SquadViewModel(
    private val squadInteractor: SquadInteractor
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _players by lazy { MutableLiveData<List<Players>>() }
    val players: LiveData<List<Players>> = _players

    fun getPlayers(teamId: Int) {
        compositeDisposable.add(
            squadInteractor.getPlayers(teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    Log.e("RESULT", result.toString())
                    _players.value = result
                },
                    { error -> Log.e("ERROR", error.toString()) })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}
