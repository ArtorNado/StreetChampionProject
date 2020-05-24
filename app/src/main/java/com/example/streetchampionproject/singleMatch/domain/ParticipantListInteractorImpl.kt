package com.example.streetchampionproject.singleMatch.domain

import com.example.streetchampionproject.api.scs.models.Participants
import com.example.streetchampionproject.singleMatch.data.interfaces.ParticipantListRepository
import com.example.streetchampionproject.singleMatch.domain.interfaces.ParticipantListInteractor
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ParticipantListInteractorImpl @Inject constructor(
    private val participantListRepository: ParticipantListRepository
) : ParticipantListInteractor {

    override fun getParticipants(matchId: Int): Observable<List<Participants>> =
        participantListRepository.getParticipantsLocal(matchId)

    override fun updateParticipants(matchId: Int): Completable =
        participantListRepository.updateParticipants(matchId)
}
