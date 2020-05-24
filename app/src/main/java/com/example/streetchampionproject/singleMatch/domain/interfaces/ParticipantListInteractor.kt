package com.example.streetchampionproject.singleMatch.domain.interfaces

import com.example.streetchampionproject.api.scs.models.Participants
import io.reactivex.Completable
import io.reactivex.Observable

interface ParticipantListInteractor {

    fun getParticipants(matchId: Int): Observable<List<Participants>>

    fun updateParticipants(matchId: Int): Completable
}
