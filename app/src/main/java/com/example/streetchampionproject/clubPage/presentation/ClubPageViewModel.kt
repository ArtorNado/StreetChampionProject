package com.example.streetchampionproject.clubPage.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.api.scs.response.Teams
import com.example.streetchampionproject.clubPage.domain.interfaces.ClubPageInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ClubPageViewModel(
    private val clubPageInteractor: ClubPageInteractor,
    private val id: Int
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _team = MutableLiveData<Teams>()
    val team: LiveData<Teams> = _team

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    fun getData() {
        compositeDisposable.add(
            clubPageInteractor.getTeam(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    Log.e("RESULT", result.toString())
                    _team.value = result
                },
                    { error -> Log.e("ERROR", error.toString()) })
        )
    }

    fun determineRole(teamId: Int) {
        compositeDisposable.add(
            clubPageInteractor.determineUserStatusInTeam(teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _status.value = result.status
                },
                    { error -> Log.e("ERROR", error.toString()) })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
