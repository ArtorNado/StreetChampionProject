package com.example.streetchampionproject.main.presentation.ui.clubs.presentation

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.main.presentation.ui.clubs.domain.interfaces.ClubListInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class ClubListViewModel(
    private val clubListInteractor: ClubListInteractor,
    private val navigator: Navigator
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _clubList by lazy { MutableLiveData<List<Teams>>() }
    var clubList: LiveData<List<Teams>> = _clubList

    private val _searchError by lazy { MutableLiveData<String>() }
    var searchError: LiveData<String> = _searchError

    private val _pgStatus by lazy { MutableLiveData<Int>() }
    val pgStatus: LiveData<Int> = _pgStatus

    init {
        getData()
    }

    fun getData() {
        _pgStatus.value = View.VISIBLE
        updateTeams()
        compositeDisposable.add(
            clubListInteractor.getAllTeams()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _clubList.value = result
                    _pgStatus.value = View.GONE
                },
                    { error ->
                        _pgStatus.value = View.GONE
                    })
        )
    }

    fun getTeamsByCity(city: String) {
        _pgStatus.value = View.VISIBLE
        updateTeamsByCity(city)
        compositeDisposable.add(
            clubListInteractor.getTeamsByCity(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _clubList.value = result
                    _pgStatus.value = View.GONE
                },
                    { error ->
                        Log.e("ERROR11", error.toString())
                        doOnError(error)
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
                    {
                        doOnError(it)
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
                    {
                        doOnError(it)
                    })
        )
    }

    private fun doOnError(error: Throwable) {
        when (error) {
            is java.lang.IllegalArgumentException -> {
                _searchError.value = EMPTY_FIELD
                _pgStatus.value = View.GONE
            }
            is HttpException -> {
                _searchError.value = CITY_NOT_FOUND
                _pgStatus.value = View.GONE
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    companion object {
        private const val EMPTY_FIELD = "Enter the city name"
        private const val CITY_NOT_FOUND = "City not found"
    }

}
