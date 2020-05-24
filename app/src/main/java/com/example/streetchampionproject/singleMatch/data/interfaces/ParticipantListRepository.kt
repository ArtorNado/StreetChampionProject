package com.example.streetchampionproject.singleMatch.data.interfaces

import com.example.streetchampionproject.api.scs.models.Participants
import io.reactivex.Completable
import io.reactivex.Observable

interface ParticipantListRepository {

    fun getParticipantsLocal(matchId: Int): Observable<List<Participants>>

    fun updateParticipants(matchId: Int): Completable
}
