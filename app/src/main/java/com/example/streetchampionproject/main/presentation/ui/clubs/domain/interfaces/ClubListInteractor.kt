package com.example.streetchampionproject.main.presentation.ui.clubs.domain.interfaces

import com.example.streetchampionproject.api.scs.models.Teams
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface ClubListInteractor {

    fun getAllTeams(): Observable<List<Teams>>

    fun getTeamsByCity(city: String): Single<List<Teams>>

    fun updateTeamsList(): Completable

    fun updateTeamsListByCity(city: String): Single<List<Teams>>
}
