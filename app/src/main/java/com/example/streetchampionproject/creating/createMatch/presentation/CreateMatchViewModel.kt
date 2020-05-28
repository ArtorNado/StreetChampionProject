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

    private val _event: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val event: LiveData<String> = _event

    fun createCommandMatch(createCommandMatch: CreateCommandMatch) {
        compositeDisposable.add(
            createMatchInteractor.createCommandMatch(createCommandMatch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                },
                    {error ->
                        onError(error)
                    })
        )
    }

    fun createSingleMatch(createSingleMatch: CreateSingleMatch) {
        compositeDisposable.add(
            createMatchInteractor.createSingleMatch(createSingleMatch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                },
                    { error ->
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
                    if (result.status != "Admin") _event.value = "Go back"
                },
                    { error ->
                        _event.value = "Go back"
                        onError(error)
                    })
        )
    }

}
