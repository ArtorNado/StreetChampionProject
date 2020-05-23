package com.example.streetchampionproject.match.data

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.common.data.databse.dao.MatchCommandDao
import com.example.streetchampionproject.common.data.databse.dao.MatchSingleDao
import com.example.streetchampionproject.common.domain.sharedPreference.LocalStorage
import com.example.streetchampionproject.match.data.interfaces.MatchListRepository
import com.example.streetchampionproject.match.data.mappers.*
import com.example.streetchampionproject.match.data.models.MatchCommandRemote
import com.example.streetchampionproject.match.data.models.MatchSingleRemote
import io.reactivex.Single
import javax.inject.Inject

class MatchListRepositoryImpl @Inject constructor(
    private val streetChampionService: StreetChampionService,
    private val localStorage: LocalStorage,
    private val matchSingleListDao: MatchSingleDao,
    private val matchCommandDao: MatchCommandDao
) : MatchListRepository {

    val userId: Int = localStorage.readMessage("userId")?.toInt() ?: 0

    override fun updateSingleMatchListWithRole(role: String): Single<List<Any?>> =
        streetChampionService.getSingleMatchByRole(userId, role)
            .map { mapMatchSingleRemoteToMatchSingle(it) }

    override fun updateSingleMatchWithoutRole(): Single<List<Any?>> =
        streetChampionService.getMatchSingle()
            .doOnSuccess { setSingleMatchLocal(it) }
            .map { mapMatchSingleRemoteToMatchSingle(it) }

    override fun getSingleMatchLocal(role: String): Single<List<Any?>> =
        matchSingleListDao.getSingleMatch(role)
            .map { mapMatchEntityToMatchSingle(it) }


    private fun setSingleMatchLocal(matchSingleRemote: List<MatchSingleRemote>) {
        matchSingleListDao.setSingleMatchs(
            mapMatchSingleRemoteToMatchSingleEntity(
                matchSingleRemote,
                "Undefined"
            )
        )
    }

    override fun getCommandMatchLocal(role: String): Single<List<Any?>> =
        matchCommandDao.getCommandMatchList(role)
            .map { mapMatchEntityToCommand(it) }


    override fun updateCommandMatchWithRole(role: String): Single<List<Any?>> =
        streetChampionService.getCommandMatchByRole(userId, role)
            .map { mapMatchRemoteToMatch(it) }


    override fun updateCommandMatchWithoutRole(): Single<List<Any?>> =
        streetChampionService.getCommandMatch()
            .doOnSuccess { setCommandMatchLocal(it) }
            .map { mapMatchRemoteToMatch(it) }


    private fun setCommandMatchLocal(matchCommandRemote: List<MatchCommandRemote>) {
        matchCommandDao.setCommandMatchList(mapMatchRemoteToEntity(matchCommandRemote, "Undefined"))
    }

}
