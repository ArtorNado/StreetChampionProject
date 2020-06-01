package com.example.streetchampionproject.match.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.R
import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import com.example.streetchampionproject.match.domain.interfaces.MatchListInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MatchListViewModel(
    private val matchListInteractor: MatchListInteractor
) : BaseViewModel() {

    private val _matchList: MutableLiveData<List<Any?>> by lazy { MutableLiveData<List<Any?>>() }
    val matchList: LiveData<List<Any?>> = _matchList

    private val _pgStatus: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val pgStatus: LiveData<String> = _pgStatus

    private var searchCity: String = "Undefined"
    var chMatchType: Int? = null
    var chStatus: Int? = null
    var backStatus = true

    private fun getMatchList(matchType: String, role: String) {
        compositeDisposable.add(
            matchListInteractor.getMatchList(matchType, role, searchCity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _matchList.value = result
                },
                    { error ->
                        compositeDisposable.clear()
                        onError(error)
                    })
        )
        updateMatchList(matchType, role)
    }

    private fun updateMatchList(matchType: String, role: String) {
        _pgStatus.value = ARG_STATUS_VISIBLE
        compositeDisposable.add(
            matchListInteractor.updateMatchList(matchType, role, searchCity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _pgStatus.value = ARG_STATUS_GONE
                    _matchList.value = result
                },
                    { error ->
                        _pgStatus.value = ARG_STATUS_GONE
                        onError(error)
                    })
        )
    }

    fun getData(matchType: Int, status: Int) {
        chMatchType = matchType
        chStatus = status
        val match = determineMatchType(matchType)
        val role = determineStatus(status)
        updateMatchList(match, role)
    }

    fun getDataAfterBackState(){
        getDataByCity(chMatchType?:0, chStatus?:0, searchCity)
        backStatus = true
    }

    fun getDataByCity(matchType: Int, status: Int, city: String){
        chMatchType = matchType
        chStatus = status
        val match = determineMatchType(matchType)
        val role = determineStatus(status)
        if(city == "") this.searchCity = "Undefined"
        else this.searchCity = city
        getMatchList(match, role)
    }

    private fun determineMatchType(id: Int): String {
        return when (id) {
            CHIP_SINGLE -> "Single"
            CHIP_COMMAND -> "Command"
            else -> "Undefined"
        }
    }

    private fun determineStatus(id: Int): String {
        return when (id) {
            CHIP_FREE_MATCH -> "Free"
            CHIP_PARTICIPANT -> "Participant"
            CHIP_ADMIN -> "Admin"
            else -> "Undefined"
        }
    }

    companion object {
        const val CHIP_FREE_MATCH = R.id.ch_free_mathes
        const val CHIP_ADMIN = R.id.ch_admin
        const val CHIP_PARTICIPANT = R.id.ch_participant
        const val CHIP_SINGLE = R.id.ch_single
        const val CHIP_COMMAND = R.id.ch_team
        const val ARG_STATUS_GONE = "Gone"
        const val ARG_STATUS_VISIBLE = "Visible"
    }

}
