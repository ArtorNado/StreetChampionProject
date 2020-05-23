package com.example.streetchampionproject.match.presentation

import android.view.View
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

    private val _error: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val error: LiveData<String> = _error

    private val _status: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val status: LiveData<Int> = _status

    private fun getMatchList(matchType: String, role: String) {
        compositeDisposable.add(
            matchListInteractor.getMatchList(matchType, role)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _matchList.value = result
                },
                    { error ->
                        compositeDisposable.clear()
                        _status.value = View.GONE
                        _error.value = error.toString()
                    })
        )
        updateMatchList(matchType, role)
    }

    private fun updateMatchList(matchType: String, role: String) {
        compositeDisposable.add(
            matchListInteractor.updateMatchList(matchType, role)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _matchList.value = result
                },
                    { error ->
                        _error.value = error.toString()
                    })
        )
    }

    fun getData(matchType: Int, status: Int) {
        val match = determineMatchType(matchType)
        val role = determineStatus(status)
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
    }

}