package com.example.streetchampionproject.main.presentation.ui.clubs.data.interfaces

import com.example.streetchampionproject.api.scs.models.Teams
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface ClubListRepository {

    fun getTeamsLocal(): Observable<List<Teams>>

    fun getTeamsByCityLocal(city: String): Single<List<Teams>>

    fun updateTeams(): Completable

    fun updateTeamsByCity(city: String): Single<List<Teams>>

}
