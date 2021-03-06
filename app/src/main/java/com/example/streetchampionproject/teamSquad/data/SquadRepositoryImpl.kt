package com.example.streetchampionproject.teamSquad.data

import com.example.streetchampionproject.api.scs.models.Players
import com.example.streetchampionproject.common.data.databse.dao.SquadDao
import com.example.streetchampionproject.common.data.databse.models.SquadEntity
import com.example.streetchampionproject.teamSquad.data.interfaces.SquadRepository
import com.example.streetchampionproject.teamSquad.data.mappers.mapSquadEntityToPlayers
import com.example.streetchampionproject.teamSquad.data.mappers.mapSquadRemoteToSquadEntity
import com.example.streetchampionproject.teamSquad.data.network.SquadService
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class SquadRepositoryImpl @Inject constructor(
    private val squadService: SquadService,
    private val squadDao: SquadDao
) : SquadRepository {

    override fun getPlayersLocal(teamId: Int): Observable<List<Players>> =
        squadDao.getSquad(teamId)
            .map {
                mapSquadEntityToPlayers(it)
            }

    override fun updatePlayers(teamId: Int): Completable =
        squadService.getPlayers(teamId)
            .map {
                setPlayersLocal(mapSquadRemoteToSquadEntity(it, teamId))
            }
            .ignoreElement()

    private fun setPlayersLocal(squadEntity: List<SquadEntity>) {
        squadDao.setSquad(squadEntity)
    }
}
