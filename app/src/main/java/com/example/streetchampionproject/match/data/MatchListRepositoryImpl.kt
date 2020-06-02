package com.example.streetchampionproject.match.data

import com.example.streetchampionproject.common.data.databse.dao.MatchCommandDao
import com.example.streetchampionproject.common.data.databse.dao.MatchSingleDao
import com.example.streetchampionproject.common.domain.Exceptions
import com.example.streetchampionproject.common.domain.ResponseCode
import com.example.streetchampionproject.common.domain.sharedPreference.LocalStorage
import com.example.streetchampionproject.match.data.interfaces.MatchListRepository
import com.example.streetchampionproject.match.data.mappers.*
import com.example.streetchampionproject.match.data.models.MatchCommandRemote
import com.example.streetchampionproject.match.data.models.MatchSingleRemote
import com.example.streetchampionproject.match.data.network.MatchListService
import io.reactivex.Single
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class MatchListRepositoryImpl @Inject constructor(
    private val matchListService: MatchListService,
    private val localStorage: LocalStorage,
    private val matchSingleListDao: MatchSingleDao,
    private val matchCommandDao: MatchCommandDao
) : MatchListRepository {

    val userId: Int = localStorage.readMessage("userId")?.toInt() ?: 0

    override fun updateSingleMatchListWithRole(role: String): Single<List<Any?>> =
        matchListService.getSingleMatchByRole(userId, role)
            .onErrorResumeNext { error ->
                Single.error(onError(error))
            }
            .map { mapMatchSingleRemoteToMatchSingle(it) }

    override fun updateSingleMatchWithoutRole(): Single<List<Any?>> =
        matchListService.getMatchSingle()
            .doOnSuccess { setSingleMatchLocal(it) }
            .onErrorResumeNext { error ->
                Single.error(onError(error))
            }
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
        matchListService.getCommandMatchByRole(userId, role)
            .onErrorResumeNext { error ->
                Single.error(onError(error))
            }
            .map { mapMatchRemoteToMatch(it) }


    override fun updateCommandMatchWithoutRole(): Single<List<Any?>> =
        matchListService.getCommandMatch()
            .doOnSuccess { setCommandMatchLocal(it) }
            .onErrorResumeNext { error ->
                Single.error(onError(error))
            }
            .map { mapMatchRemoteToMatch(it) }

    override fun getCommandMatchByCityLocal(city: String): Single<List<Any?>> =
        matchCommandDao.getCommandMatchByCity(city)
            .map { mapMatchEntityToCommand(it) }

    override fun getCommandMatchByRoleAndCityLocal(role: String, city: String): Single<List<Any?>> =
        matchCommandDao.getCommandMatchByRoleAndCity(role, city)
            .map { mapMatchEntityToCommand(it) }

    override fun updateCommandMatchByCity(city: String): Single<List<Any?>> =
        matchListService.getCommandMatchByCity(city)
            .onErrorResumeNext { error ->
                Single.error(onError(error))
            }
            .map { mapMatchRemoteToMatch(it) }

    override fun updateCommandMatchByRoleAndCity(role: String, city: String): Single<List<Any?>> =
        matchListService.getCommandMatchByRoleAndCity(userId, role, city)
            .onErrorResumeNext { error ->
                Single.error(onError(error))
            }
            .map { mapMatchRemoteToMatch(it) }

    override fun getSingleMatchByCity(city: String): Single<List<Any?>> =
        matchSingleListDao.getSingleMatchByCity(city)
            .map { mapMatchEntityToMatchSingle(it) }

    override fun getSingleMatchByRoleAndCity(role: String, city: String): Single<List<Any?>> =
        matchSingleListDao.getSingleMatchByRoleAndCity(role, city)
            .map { mapMatchEntityToMatchSingle(it) }

    override fun updateSingleMatchByCity(city: String): Single<List<Any?>> =
        matchListService.getSingleMatchByCity(city)
            .onErrorResumeNext { error ->
                Single.error(onError(error))
            }
            .map { mapMatchSingleRemoteToMatchSingle(it) }

    override fun updateSingleMatchByRoleAndCity(
        role: String,
        city: String
    ): Single<List<Any?>> =
        matchListService.getSingleMatchByRoleAndCity(userId, role, city)
            .onErrorResumeNext { error ->
                Single.error(onError(error))
            }
            .map { mapMatchSingleRemoteToMatchSingle(it) }


    private fun setCommandMatchLocal(matchCommandRemote: List<MatchCommandRemote>) {
        matchCommandDao.setCommandMatchList(mapMatchRemoteToEntity(matchCommandRemote, "Undefined"))
    }

    private fun onError(error: Throwable): Exceptions {
        return when (error) {
            is UnknownHostException -> Exceptions.error(ResponseCode.INTERNET_ERROR)
            is HttpException -> Exceptions.error(ResponseCode.CITY_NOT_FOUND)
            else -> Exceptions.error(ResponseCode.SERVER_ERROR)
        }
    }
}
