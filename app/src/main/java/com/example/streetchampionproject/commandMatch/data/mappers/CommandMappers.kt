package com.example.streetchampionproject.commandMatch.data.mappers

import com.example.streetchampionproject.api.scs.models.MatchCommand
import com.example.streetchampionproject.common.data.databse.models.MatchCommandEntity
import com.example.streetchampionproject.match.data.models.MatchCommandRemote

fun mapCommandMatchEntityToLocal(matchCommandEntity: MatchCommandEntity): MatchCommand {
    return with(matchCommandEntity) {
        MatchCommand(
            matchId,
            date,
            time,
            creatorId,
            firstTeamId,
            secondTeamId,
            matchCity,
            description
        )
    }
}

fun mapCommandMatchRemoteToEntity(matchCommandRemote: MatchCommandRemote): MatchCommandEntity {
    return with(matchCommandRemote) {
        MatchCommandEntity(
            matchId,
            date,
            time,
            creatorId,
            firstTeamId,
            secondTeamId,
            matchCity,
            description,
            "Undefined"
        )
    }
}
