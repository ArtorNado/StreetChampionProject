package com.example.streetchampionproject.matchHistory.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.R
import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import com.example.streetchampionproject.matchHistory.domain.interfaces.MatchHistoryInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MatchHistoryViewModel(
    private val matchHistoryInteractor: MatchHistoryInteractor,
    private val teamId: Int
) : BaseViewModel() {

    private val _matchList: MutableLiveData<List<Any?>> by lazy { MutableLiveData<List<Any?>>() }
    val matchList: LiveData<List<Any?>> = _matchList

    private val _error: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val error: LiveData<String> = _error

    private fun getEndedCommandMatch(matchType: String) {
        compositeDisposable.add(
            matchHistoryInteractor.getEndedCommandMatchLocal(matchType, teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _matchList.value = result
                },
                    { error ->
                        onError(error)
                        compositeDisposable.clear()
                    })
        )
        updateData(matchType)
    }

    private fun updateData(matchType: String) {
        compositeDisposable.add(
            matchHistoryInteractor.updateEndedCommandMatch(matchType, teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                },
                    { error ->
                        onError(error)
                    })
        )
    }

    fun getData(matchType: Int) {
        getEndedCommandMatch(determineMatchType(matchType))
    }

    private fun determineMatchType(matchType: Int): String {
        return when (matchType) {
            CHIP_ENDED -> "Ended"
            CHIP_FEATURE -> "Feature"
            else -> "Undefined"
        }
    }


    companion object {
        private const val CHIP_ENDED = R.id.ch_past
        private const val CHIP_FEATURE = R.id.ch_feature
    }

}
