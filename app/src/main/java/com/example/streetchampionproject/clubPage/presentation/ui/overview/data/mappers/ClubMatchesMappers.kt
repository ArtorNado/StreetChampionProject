package com.example.streetchampionproject.clubPage.presentation.ui.overview.data.mappers

import com.example.streetchampionproject.api.scs.models.EndedCommandMatch
import com.example.streetchampionproject.clubPage.presentation.ui.overview.data.models.EndedCommandMatchRemote
import com.example.streetchampionproject.common.data.databse.models.EndedCommandMatchEntity

fun mapEndedRemoteToEntity(endedCommandMatchRemote: List<EndedCommandMatchRemote>): List<EndedCommandMatchEntity> {
    val list = ArrayList<EndedCommandMatchEntity>()
    endedCommandMatchRemote.forEach {
        list.add(
            EndedCommandMatchEntity(
                it.matchId,
                it.date,
                it.winTeamId,
                it.firstTeam.teamName,
                it.goalsFirstTeam,
                it.secondTeam.teamName,
                it.goalsSecondTeam,
                it.firstTeam.teamId,
                it.secondTeam.teamId
            )
        )
    }
    return list
}

fun mapEndedEntityToLocal(endedCommandMatchEntity: List<EndedCommandMatchEntity>): List<EndedCommandMatch> {
    val list = ArrayList<EndedCommandMatch>()
    endedCommandMatchEntity.forEach {
        list.add(
            EndedCommandMatch(
                it.matchId,
                it.date,
                it.winTeamId,
                it.firstTeamName,
                it.goalsFirstTeam,
                it.secondTeamName,
                it.goalsSecondTeam
            )
        )
    }
    return list
}
