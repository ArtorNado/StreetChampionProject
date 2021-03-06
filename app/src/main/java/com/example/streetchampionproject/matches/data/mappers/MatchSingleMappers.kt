package com.example.streetchampionproject.matches.data.mappers

import com.example.streetchampionproject.api.scs.models.MatchSingle
import com.example.streetchampionproject.common.data.databse.models.MatchSingleEntity
import com.example.streetchampionproject.matches.data.models.MatchSingleRemote


fun mapMatchEntityToMatchSingle(matchSingleEntity: List<MatchSingleEntity>): List<MatchSingle> {
    val list = ArrayList<MatchSingle>()
    matchSingleEntity.forEach {
        list.add(
            MatchSingle(
                it.matchId,
                it.date,
                it.numberParticipant,
                it.currentNumberParticipant,
                it.matchCity
            )
        )
    }
    return list
}

fun mapMatchSingleRemoteToMatchSingleEntity(
    matchSingleRemote: List<MatchSingleRemote>,
    role: String
): List<MatchSingleEntity> {
    val list = ArrayList<MatchSingleEntity>()
    matchSingleRemote.forEach {
        list.add(
            MatchSingleEntity(
                it.matchId,
                it.date,
                it.time,
                it.creatorId,
                it.numberParticipant,
                it.currentNumberParticipant,
                it.description ?: "Without description",
                it.matchCity ?: "Undefined",
                role
            )
        )
    }
    return list
}

fun mapMatchSingleRemoteToMatchSingle(matchSingleRemote: List<MatchSingleRemote>): List<MatchSingle> {
    val list = ArrayList<MatchSingle>()
    matchSingleRemote.forEach {
        list.add(
            MatchSingle(
                it.matchId,
                it.date,
                it.numberParticipant,
                it.currentNumberParticipant,
                it.matchCity ?: "Undefined"
            )
        )
    }
    return list
}
