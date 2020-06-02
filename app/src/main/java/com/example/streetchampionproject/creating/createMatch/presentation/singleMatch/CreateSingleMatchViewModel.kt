package com.example.streetchampionproject.creating.createMatch.presentation.singleMatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.api.scs.models.CreateSingleMatch
import com.example.streetchampionproject.common.presentation.CONSTANTS
import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import com.example.streetchampionproject.creating.createMatch.domain.interfaces.CreateMatchInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CreateSingleMatchViewModel(
    private val createMatchInteractor: CreateMatchInteractor
) : BaseViewModel() {

    private val _status: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val status: LiveData<String> = _status

    private val _goTo: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val goTo: LiveData<String> = _goTo

    fun createSingleMatch(createSingleMatch: CreateSingleMatch) {
        _status.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE
        compositeDisposable.add(
            createMatchInteractor.createSingleMatch(createSingleMatch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _status.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                    onNotification("Матч создан")
                    _goTo.value = CONSTANTS.ACTION.EVENT_GO_BACK
                },
                    { error ->
                        _status.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                        onError(error)
                    })
        )
    }

}
