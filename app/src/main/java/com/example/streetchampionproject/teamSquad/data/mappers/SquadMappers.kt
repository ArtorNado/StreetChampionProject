package com.example.streetchampionproject.teamSquad.data.mappers

import com.example.streetchampionproject.api.scs.models.Players
import com.example.streetchampionproject.common.data.databse.models.SquadEntity
import com.example.streetchampionproject.teamSquad.data.models.SquadRemote

fun mapSquadEntityToPlayers(squadEntity: List<SquadEntity>): List<Players> {
    val list = ArrayList<Players>()
    squadEntity.forEach {
        list.add(
            Players(
                it.userId,
                it.firstName,
                it.secondName,
                it.userCity
            )
        )
    }
    return list
}

fun mapSquadRemoteToSquadEntity(squadRemote: List<SquadRemote>, teamId: Int): List<SquadEntity> {
    val list = ArrayList<SquadEntity>()
    squadRemote.forEach {
        list.add(
            SquadEntity(
                it.userId,
                it.firstName,
                it.secondName,
                it.userCity,
                teamId
            )
        )
    }
    return list
}
