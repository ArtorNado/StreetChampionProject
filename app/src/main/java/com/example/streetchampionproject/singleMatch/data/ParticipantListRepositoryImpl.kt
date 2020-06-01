package com.example.streetchampionproject.singleMatch.data

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.api.scs.models.Participants
import com.example.streetchampionproject.common.data.databse.dao.ParticipantsDao
import com.example.streetchampionproject.common.data.databse.models.ParticipantsEntity
import com.example.streetchampionproject.singleMatch.data.interfaces.ParticipantListRepository
import com.example.streetchampionproject.singleMatch.data.mappers.mapParticipantsEntityToUserParticipants
import com.example.streetchampionproject.singleMatch.data.mappers.mapParticipantsRemoteToPartEntity
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ParticipantListRepositoryImpl @Inject constructor(
    private val streetChampionService: StreetChampionService,
    private val participantsDao: ParticipantsDao
) : ParticipantListRepository {

    override fun getParticipantsLocal(matchId: Int): Observable<List<Participants>> =
        participantsDao.getParticipants(matchId)
            .map { mapParticipantsEntityToUserParticipants(it) }

    override fun updateParticipants(matchId: Int): Completable {
        return streetChampionService.getParticipants(matchId)
            .map {
                setParticipantsLocal(mapParticipantsRemoteToPartEntity(it, matchId)) }
            .ignoreElement()
    }

    private fun setParticipantsLocal(participantsEntity: List<ParticipantsEntity>) {
        participantsDao.setParticipants(participantsEntity)
    }
}
