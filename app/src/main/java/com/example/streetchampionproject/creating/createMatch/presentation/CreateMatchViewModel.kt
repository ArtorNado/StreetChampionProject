package com.example.streetchampionproject.creating.createMatch.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.api.scs.models.CreateCommandMatch
import com.example.streetchampionproject.api.scs.models.CreateSingleMatch
import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import com.example.streetchampionproject.creating.createMatch.domain.interfaces.CreateMatchInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CreateMatchViewModel(
    private val createMatchInteractor: CreateMatchInteractor
) : BaseViewModel() {

    private val _status: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val status: LiveData<String> = _status

    private val _goTo: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val goTo: LiveData<String> = _goTo


    fun createCommandMatch(createCommandMatch: CreateCommandMatch) {
        _status.value = ARG_STATUS_VISIBLE
        compositeDisposable.add(
            createMatchInteractor.createCommandMatch(createCommandMatch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _status.value = ARG_STATUS_GONE
                    onNotification("Матч создан")
                    _goTo.value = "Go back"
                },
                    {error ->
                        _status.value = ARG_STATUS_GONE
                        onError(error)
                    })
        )
    }

    fun createSingleMatch(createSingleMatch: CreateSingleMatch) {
        _status.value = ARG_STATUS_VISIBLE
        compositeDisposable.add(
            createMatchInteractor.createSingleMatch(createSingleMatch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _status.value = ARG_STATUS_GONE
                    onNotification("Матч создан")
                    _goTo.value = "Go back"
                },
                    { error ->
                        _status.value = ARG_STATUS_GONE
                        onError(error)
                    })
        )
    }

    fun determineUserStatus() {
        compositeDisposable.add(
            createMatchInteractor.determineUserRole()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    if (result.status != "Admin") _goTo.value = "Go back"
                },
                    { error ->
                        _goTo.value = "Go back"
                        onError(error)
                    })
        )
    }

    companion object {
        const val ARG_STATUS_GONE = "Gone"
        const val ARG_STATUS_VISIBLE = "Visible"
    }

}
