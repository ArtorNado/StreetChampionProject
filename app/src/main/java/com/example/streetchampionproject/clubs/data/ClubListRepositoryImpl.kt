package com.example.streetchampionproject.clubs.data

import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.clubs.data.interfaces.ClubListRepository
import com.example.streetchampionproject.clubs.data.mappers.mapTeamsEntityToTeams
import com.example.streetchampionproject.clubs.data.mappers.mapTeamsRemoteToTeams
import com.example.streetchampionproject.clubs.data.mappers.mapTeamsRemoteToTeamsEntity
import com.example.streetchampionproject.clubs.data.network.ClubsService
import com.example.streetchampionproject.common.data.databse.dao.TeamsDao
import com.example.streetchampionproject.common.data.databse.models.TeamsEntity
import com.example.streetchampionproject.common.domain.Exceptions
import com.example.streetchampionproject.common.domain.ResponseCode
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class ClubListRepositoryImpl @Inject constructor(
    private val clubsService: ClubsService,
    private val teamsDao: TeamsDao
) : ClubListRepository {

    override fun getTeamsLocal(): Observable<List<Teams>> = teamsDao.getAllTeams()
        .map { mapTeamsEntityToTeams(it) }

    override fun getTeamsByCityLocal(city: String): Single<List<Teams>> =
        teamsDao.getTeamsByCity(city).map { mapTeamsEntityToTeams(it) }

    override fun updateTeams(): Completable =
        clubsService.getTeams()
            .map { setTeamsLocal(mapTeamsRemoteToTeamsEntity(it)) }
            .onErrorResumeNext { error ->
                Single.error(onError(error))
            }
            .ignoreElement()

    override fun updateTeamsByCity(city: String): Single<List<Teams>> =
        clubsService.getTeamsByCity(city)
            .map { mapTeamsRemoteToTeams(it) }
            .onErrorResumeNext { error ->
                Single.error(onError(error))
            }

    private fun setTeamsLocal(teamsEntity: List<TeamsEntity>) {
        teamsDao.clear()
        teamsDao.setTeams(teamsEntity)
    }

    private fun onError(error: Throwable) =
        when (error) {
            is UnknownHostException -> Exceptions.error(ResponseCode.INTERNET_ERROR)
            is HttpException -> Exceptions.error(ResponseCode.CITY_NOT_FOUND)
            else -> Exceptions.error(ResponseCode.SERVER_ERROR)
        }


}
