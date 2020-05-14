package com.example.streetchampionproject.main.presentation.ui.clubs.data.interfaces

import com.example.streetchampionproject.api.scs.models.Teams
import io.reactivex.Completable
import io.reactivex.Observable

interface ClubListRepository {

    fun getTeamsLocal(): Observable<List<Teams>>

    fun getTeamsByCityLocal(city: String): Observable<List<Teams>>

    fun updateTeams(): Completable

    fun updateTeamsByCity(city: String): Completable

}
