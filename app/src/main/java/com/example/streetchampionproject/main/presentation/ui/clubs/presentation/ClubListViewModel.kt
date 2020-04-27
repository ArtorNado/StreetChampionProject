package com.example.streetchampionproject.main.presentation.ui.clubs.presentation

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.response.Teams
import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.main.presentation.ui.clubs.domain.interfaces.ClubListInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ClubListViewModel(
    private val clubListInteractor: ClubListInteractor,
    private val navigator: Navigator
) : ViewModel() {

    private var _clubList = MutableLiveData<List<Teams>>()
    var clubList: LiveData<List<Teams>> = _clubList

    private var _searchError = MutableLiveData<String>()
    var searchError: LiveData<String> = _searchError

    init{
        val result = clubListInteractor.getTeams()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                _clubList.value = result
            },
                { error ->
                })
    }

    fun clickOnClub(navController: NavController, id: Int){
        val bundle = Bundle()
        bundle.putInt("teamId", id)
        Log.e("id", id.toString())
        navController.navigate(R.id.action_navigation_notifications_to_clubPageFragment, bundle)
    }

    fun getTeamsByName(name: String){
        val result = clubListInteractor.getTeamsByName(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                _clubList.value = result
            },
                { error ->
                    Log.e("ERROR11",error.toString())
                })
    }

    fun getTeamsByCity(city: String){
        val result = clubListInteractor.getTeamsByCity(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                _clubList.value = result
            },
                { error ->
                    Log.e("ERROR11",error.toString())
                    errorSearchType(error)
                })
    }

    private fun errorSearchType(error: Throwable){
        when(error){
            is java.lang.IllegalArgumentException -> {
                Log.e("PRINT", "PRINT")
                _searchError.value = EMPTY_FIELD
            }
        }
    }

    companion object{
        private const val EMPTY_FIELD = "Enter the city name"
    }

}
