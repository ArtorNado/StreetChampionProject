package com.example.streetchampionproject.main.presentation.ui.clubs.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.api.scs.response.Teams
import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.main.presentation.ui.clubs.domain.interfaces.ClubListInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class ClubListViewModel(
    private val clubListInteractor: ClubListInteractor,
    private val navigator: Navigator
) : ViewModel() {

    private var _clubList = MutableLiveData<List<Teams>>()
    var clubList: LiveData<List<Teams>> = _clubList

    private var _searchError = MutableLiveData<String>()
    var searchError: LiveData<String> = _searchError

    fun getData() {
        val result = clubListInteractor.getTeams()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                _clubList.value = result
            },
                { error ->
                })
    }

    fun getTeamsByCity(city: String) {
        val result = clubListInteractor.getTeamsByCity(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                _clubList.value = result
            },
                { error ->
                    Log.e("ERROR11", error.toString())
                    errorSearchType(error)
                })
    }

    private fun errorSearchType(error: Throwable) {
        when (error) {
            is java.lang.IllegalArgumentException -> {
                _searchError.value = EMPTY_FIELD
            }
            is HttpException -> {
                _searchError.value = CITY_NOT_FOUND
            }
        }
    }

    companion object {
        private const val EMPTY_FIELD = "Enter the city name"
        private const val CITY_NOT_FOUND = "City not found"
    }

}
