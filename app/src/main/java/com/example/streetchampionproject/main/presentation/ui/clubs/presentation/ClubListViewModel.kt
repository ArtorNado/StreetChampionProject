package com.example.streetchampionproject.main.presentation.ui.clubs.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import com.example.streetchampionproject.main.presentation.ui.clubs.domain.interfaces.ClubListInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ClubListViewModel(
    private val clubListInteractor: ClubListInteractor,
    private val navigator: Navigator
) : BaseViewModel() {

    private val _clubList by lazy { MutableLiveData<List<Teams>>() }
    var clubList: LiveData<List<Teams>> = _clubList

    private val _searchError by lazy { MutableLiveData<String>() }
    var searchError: LiveData<String> = _searchError

    private val _pgStatus by lazy { MutableLiveData<Int>() }
    val pgStatus: LiveData<Int> = _pgStatus

    init {
        getData()
    }

    private fun getData() {
        updateTeams()
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
        updateTeamsByCity(city)
        compositeDisposable.add(
            clubListInteractor.getTeamsByCity(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _clubList.value = result
                },
                    { error ->
                        Log.e("ERROR11", error.toString())
                        onError(error)
                    })
        )
    }

    private fun updateTeams() {
        compositeDisposable.add(
            clubListInteractor.updateTeamsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                },
                    {error ->
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
                },
                    { error ->
                        onError(error)
                    })
        )
    }

    companion object {
        private const val EMPTY_FIELD = "Enter the city name"
        private const val CITY_NOT_FOUND = "City not found"
    }

}
