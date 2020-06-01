package com.example.streetchampionproject.creating.createTeam.presentation

import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import com.example.streetchampionproject.creating.createTeam.domain.interfaces.CreateTeamInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CreateTeamViewModel(
    private val createTeamInteractor: CreateTeamInteractor
) : BaseViewModel() {

    fun createTeam(teamName: String, teamCity: String) {
        compositeDisposable.add(
            createTeamInteractor.createTeam(teamName, teamCity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                },
                    {error ->
                        onError(error)
                    })
        )
    }
}
