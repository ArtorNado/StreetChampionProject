package com.example.streetchampionproject.main.presentation.ui.clubs.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import com.example.streetchampionproject.main.presentation.ui.clubs.domain.interfaces.ClubListInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ClubListViewModel(
    private val clubListInteractor: ClubListInteractor
) : BaseViewModel() {

    private val _clubList by lazy { MutableLiveData<List<Teams>>() }
    var clubList: LiveData<List<Teams>> = _clubList

    private val _searchError by lazy { MutableLiveData<String>() }
    var searchError: LiveData<String> = _searchError

    private val _pgStatus by lazy { MutableLiveData<Int>() }
    val pgStatus: LiveData<Int> = _pgStatus

    init {
        getData()
        updateTeams()
    }

    private fun getData() {
        compositeDisposable.add(
            clubListInteractor.getAllTeams()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _clubList.value = result
                },
                    { error ->
                        onError(error)
                    })
        )
    }

    fun getTeamsByCity(city: String) {
        if (city.isEmpty()) updateTeams()
        else {
            updateTeamsByCity(city)
            compositeDisposable.add(
                clubListInteractor.getTeamsByCity(city)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ result ->
                        _clubList.value = result
                    },
                        { error ->
                            onError(error)
                        })
            )
        }
    }

    private fun updateTeams() {
        compositeDisposable.add(
            clubListInteractor.updateTeamsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                },
                    { error ->
                        onError(error)
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
