package com.example.streetchampionproject.clubPage.presentation.ui.squad.data.interfaces

import com.example.streetchampionproject.api.scs.models.Players
import io.reactivex.Completable
import io.reactivex.Observable

interface SquadRepository {

    fun getPlayersLocal(teamId: Int): Observable<List<Players>>

    fun updatePlayers(teamId: Int): Completable
}
