package com.example.streetchampionproject.teamSquad.domain.interfaces

import com.example.streetchampionproject.api.scs.models.Players
import io.reactivex.Completable
import io.reactivex.Observable

interface SquadInteractor {

    fun getPlayers(teamId: Int): Observable<List<Players>>

    fun updatePlayers(teamId: Int): Completable
}
