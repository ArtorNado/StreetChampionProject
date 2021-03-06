package com.example.streetchampionproject.matches.data.mappers

import com.example.streetchampionproject.api.scs.models.MatchCommand
import com.example.streetchampionproject.common.data.databse.models.MatchCommandEntity
import com.example.streetchampionproject.matches.data.models.MatchCommandRemote

fun mapMatchEntityToCommand(matchCommandEntity: List<MatchCommandEntity>): List<MatchCommand> {
    val list = ArrayList<MatchCommand>()
    matchCommandEntity.forEach {
        list.add(
            MatchCommand(
                it.matchId,
                it.date,
                it.time,
                it.creatorId,
                it.firstTeamId,
                it.secondTeamId,
                it.matchCity,
                it.description,
                it.firstTeamName,
                it.secondTeamName
            )
        )
    }
    return list
}

fun mapMatchRemoteToEntity(
    matchCommandRemote: List<MatchCommandRemote>,
    role: String
): List<MatchCommandEntity> {
    val list = ArrayList<MatchCommandEntity>()
    matchCommandRemote.forEach {
        list.add(
            MatchCommandEntity(
                it.matchId,
                it.date,
                it.time,
                it.creatorId,
                it.firstTeamId,
                it.secondTeamId?:0,
                it.matchCity,
                it.description,
                role,
                it.firstTeamName,
                it.secondTeamName?:""
            )
        )
    }
    return list
}

fun mapMatchRemoteToMatch(
    matchCommandRemote: List<MatchCommandRemote>
): List<MatchCommand> {
    val list = ArrayList<MatchCommand>()
    matchCommandRemote.forEach {
        list.add(
            MatchCommand(
                it.matchId,
                it.date,
                it.time,
                it.creatorId,
                it.firstTeamId,
                it.secondTeamId?:0,
                it.matchCity,
                it.description,
                it.firstTeamName,
                it.secondTeamName?:""
            )
        )
    }
    return list
}
