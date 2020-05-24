package com.example.streetchampionproject.singleMatch.data.mappers

import com.example.streetchampionproject.api.scs.models.Participants
import com.example.streetchampionproject.common.data.databse.models.ParticipantsEntity
import com.example.streetchampionproject.singleMatch.data.models.ParticipantRemote


fun mapParticipantsEntityToUserParticipants(participantsEntity: List<ParticipantsEntity>): List<Participants> {
    val list = ArrayList<Participants>()
    participantsEntity.forEach {
        list.add(
            Participants(
                it.userId,
                it.firstName,
                it.secondName,
                it.userCity
            )
        )
    }
    return list
}

fun mapParticipantsRemoteToPartEntity(participantRemote: List<ParticipantRemote>, matchId: Int):
        List<ParticipantsEntity> {
    val list = ArrayList<ParticipantsEntity>()
    participantRemote.forEach {
        list.add(
            ParticipantsEntity(
                it.userId,
                it.userFirstName,
                it.userSecondName,
                it.userCity,
                matchId
            )
        )
    }
    return list
}
