package com.example.streetchampionproject.clubPage.presentation.ui.overview.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.R
import com.example.streetchampionproject.clubPage.presentation.ui.overview.domain.interfaces.OverviewInteractor
import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class OverviewViewModel(
    private val overviewInteractor: OverviewInteractor,
    private val teamId: Int
) : BaseViewModel() {

    private val _matchList: MutableLiveData<List<Any?>> by lazy { MutableLiveData<List<Any?>>() }
    val matchList: LiveData<List<Any?>> = _matchList

    private val _error: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val error: LiveData<String> = _error


    private fun getEndedCommandMatch(matchType: String) {
        compositeDisposable.add(
            overviewInteractor.getEndedCommandMatchLocal(matchType, teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _matchList.value = result
                },
                    { error ->
                        Log.e("ERROR_GET", error.toString())
                        compositeDisposable.clear()
                    })
        )
        updateData(matchType)
    }

    private fun updateData(matchType: String) {
        compositeDisposable.add(
            overviewInteractor.updateEndedCommandMatch(matchType, teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                },
                    { error ->
                        Log.e("ERROR_UPDATE", error.toString())
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
