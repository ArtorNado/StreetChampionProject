package com.example.streetchampionproject.match.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.models.MatchSingle
import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import com.example.streetchampionproject.match.domain.interfaces.MatchListInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MatchListViewModel(
    private val matchListInteractor: MatchListInteractor
) : BaseViewModel() {

    private val _matchList: MutableLiveData<List<MatchSingle>> by lazy { MutableLiveData<List<MatchSingle>>() }
    val matchList: LiveData<List<MatchSingle>> = _matchList

    private val _error: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val error: LiveData<String> = _error


    private fun getSingleMatchList(matchType: String, role: String) {
        val result = matchListInteractor.getMatchList(matchType, role)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                _matchList.value = result
            },
                { error ->
                    _error.value = error.toString()
                    Log.e("ERROR", error.toString())
                })
        updateMatchSingle(matchType, role)
    }

    private fun updateMatchSingle(matchType: String, role: String) {
        val result = matchListInteractor.updateMatchList(matchType, role)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            },
                { error ->
                    _error.value = error.toString()
                    Log.e("ERROR", error.toString())
                })
    }

    fun getData(matchType: Int, status: Int) {
        val match = determineMatchType(matchType)
        val role = determineStatus(status)
        getSingleMatchList(match, role)
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
        const val CHIP_NOT_CHECKED = -1
        const val TYPE_SINGLE = "Single"
        const val TYPE_COMMAND = "Command"
        const val ROLE_ADMIN = "Admin"
        const val ROLE_PARTICIPANT = "Participant"
        const val CHOOSE_TYPE = "Выберите тим матча"
    }

}
