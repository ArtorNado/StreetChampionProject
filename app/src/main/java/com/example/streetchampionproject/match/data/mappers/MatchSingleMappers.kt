package com.example.streetchampionproject.match.data.mappers

import com.example.streetchampionproject.api.scs.models.MatchSingle
import com.example.streetchampionproject.common.data.databse.models.MatchSingleEntity
import com.example.streetchampionproject.match.data.models.MatchSingleRemote


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

fun mapMatchSingleRemoteToMatchSingleEntity(matchSingleRemote: List<MatchSingleRemote>): List<MatchSingleEntity> {
    val list = ArrayList<MatchSingleEntity>()
    matchSingleRemote.forEach {
        list.add(
            MatchSingleEntity(
                it.matchId,
                it.date,
                it.time,
                it.creatorId,
                it.currentNumberParticipant,
                it.numberParticipant,
                it.description?: "Without description",
                it.matchCity?: "Undefined"
            )
        )
    }
    return list
}
