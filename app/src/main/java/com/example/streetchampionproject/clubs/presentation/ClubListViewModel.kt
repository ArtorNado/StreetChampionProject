package com.example.streetchampionproject.clubs.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.clubs.domain.interfaces.ClubListInteractor
import com.example.streetchampionproject.common.presentation.CONSTANTS
import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ClubListViewModel(
    private val clubListInteractor: ClubListInteractor
) : BaseViewModel() {

    private val _clubList by lazy { MutableLiveData<List<Teams>>() }
    var clubList: LiveData<List<Teams>> = _clubList

    private val _pgStatus by lazy { MutableLiveData<String>() }
    val pgStatus: LiveData<String> = _pgStatus

    init {
        getData()
        updateTeams()
    }

    private fun getData() {
        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE
        compositeDisposable.add(
            clubListInteractor.getAllTeams()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _clubList.value = result
                    _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                },
                    { error ->
                        onError(error)
                        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                    })
        )
    }

    fun getTeamsByCity(city: String) {
        if (city.isEmpty()) updateTeams()
        else {
            _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE
            updateTeamsByCity(city)
            compositeDisposable.add(
                clubListInteractor.getTeamsByCity(city)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ result ->
                        _clubList.value = result
                        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                    },
                        { error ->
                            onError(error)
                            _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                        })
            )
        }
    }

    private fun updateTeams() {
        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE
        compositeDisposable.add(
            clubListInteractor.updateTeamsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                },
                    { error ->
                        onError(error)
                        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                    })
        )
    }

    private fun updateTeamsByCity(city: String) {
        compositeDisposable.add(
            clubListInteractor.updateTeamsListByCity(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _clubList.value = it
                },
                    { error ->
                        onError(error)
                    })
        )
    }

}
