package com.example.streetchampionproject.main.presentation.ui.clubs.data.mappers

import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.common.data.databse.models.TeamsEntity
import com.example.streetchampionproject.main.presentation.ui.clubs.data.models.TeamsRemote

fun mapTeamsEntityToTeams(teamsEntity: List<TeamsEntity>): List<Teams> {
    val list = ArrayList<Teams>()
    teamsEntity.forEach {
        list.add(
            Teams(
                it.teamId,
                it.teamName,
                it.teamCity,
                it.creatorId
            )
        )
    }
    return list
}

fun mapTeamsRemoteToTeams(teamsRemote: List<TeamsRemote>): List<Teams> {
    val list = ArrayList<Teams>()
    teamsRemote.forEach {
        list.add(
            Teams(
                it.teamId,
                it.teamName,
                it.teamCity,
                it.creatorId
            )
        )
    }
    return list
}

fun mapTeamsRemoteToTeamsEntity(teamsRemote: List<TeamsRemote>): List<TeamsEntity> {
    val list = ArrayList<TeamsEntity>()
    teamsRemote.forEach {
        list.add(
            TeamsEntity(
                it.teamId,
                it.teamName,
                it.teamCity,
                it.creatorId
            )
        )
    }
    return list
}
