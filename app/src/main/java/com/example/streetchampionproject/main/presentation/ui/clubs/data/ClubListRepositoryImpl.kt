package com.example.streetchampionproject.main.presentation.ui.clubs.data

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.common.data.databse.dao.TeamsDao
import com.example.streetchampionproject.common.data.databse.models.TeamsEntity
import com.example.streetchampionproject.main.presentation.ui.clubs.data.interfaces.ClubListRepository
import com.example.streetchampionproject.main.presentation.ui.clubs.data.mappers.mapTeamsEntityToTeams
import com.example.streetchampionproject.main.presentation.ui.clubs.data.mappers.mapTeamsRemoteToTeamsEntity
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ClubListRepositoryImpl @Inject constructor(
    private val streetChampionService: StreetChampionService,
    private val teamsDao: TeamsDao
) : ClubListRepository {

    override fun getTeamsLocal(): Observable<List<Teams>> = teamsDao.getAllTeams()
        .map { mapTeamsEntityToTeams(it) }

    override fun getTeamsByCityLocal(city: String): Observable<List<Teams>> =
        teamsDao.getTeamsByCity(city).map { mapTeamsEntityToTeams(it) }

    override fun updateTeams(): Completable =
        streetChampionService.getTeams()
            .map { setTeamsLocal(mapTeamsRemoteToTeamsEntity(it)) }
            .ignoreElement()

    override fun updateTeamsByCity(city: String): Completable =
        streetChampionService.getTeamsByCity(city)
            .map { setTeamsLocal(mapTeamsRemoteToTeamsEntity(it)) }
            .ignoreElement()

    private fun setTeamsLocal(teamsEntity: List<TeamsEntity>){
        teamsDao.clear()
        teamsDao.setTeams(teamsEntity)
    }

}
