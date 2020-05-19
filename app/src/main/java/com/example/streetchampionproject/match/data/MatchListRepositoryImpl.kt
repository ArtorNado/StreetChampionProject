package com.example.streetchampionproject.match.data

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.api.scs.models.MatchSingle
import com.example.streetchampionproject.common.data.databse.dao.MatchSingleDao
import com.example.streetchampionproject.common.data.databse.models.MatchSingleEntity
import com.example.streetchampionproject.common.domain.sharedPreference.LocalStorage
import com.example.streetchampionproject.match.data.interfaces.MatchListRepository
import com.example.streetchampionproject.match.data.mappers.mapMatchEntityToMatchSingle
import com.example.streetchampionproject.match.data.mappers.mapMatchSingleRemoteToMatchSingleEntity
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class MatchListRepositoryImpl @Inject constructor(
    private val streetChampionService: StreetChampionService,
    private val localStorage: LocalStorage,
    private val matchSingleListDao: MatchSingleDao
) : MatchListRepository {

    val userId: Int = localStorage.readMessage("userId")?.toInt() ?: 0

    override fun updateSingleMatchListWithRole(role: String): Completable =
        streetChampionService.getSingleMatchByRole(userId, role)
            .map { setSingleMatchLocal(mapMatchSingleRemoteToMatchSingleEntity(it)) }
            .ignoreElement()

    override fun updateSingleMatchWithoutRole(): Completable =
        streetChampionService.getMatchSingle()
            .map { setSingleMatchLocal(mapMatchSingleRemoteToMatchSingleEntity(it)) }
            .ignoreElement()

    override fun getSingleMatchLocal(role: String): Observable<List<MatchSingle>> =
        matchSingleListDao.getSingleMatch()
            .map { mapMatchEntityToMatchSingle(it) }

    private fun setSingleMatchLocal(matchSingleEntity: List<MatchSingleEntity>) {
        matchSingleListDao.clear()
        matchSingleListDao.setSingleMatchs(matchSingleEntity)
    }

}
