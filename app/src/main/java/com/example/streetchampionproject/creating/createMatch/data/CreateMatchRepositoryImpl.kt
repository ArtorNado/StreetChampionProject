package com.example.streetchampionproject.creating.createMatch.data

import com.example.streetchampionproject.api.scs.models.CreateCommandMatch
import com.example.streetchampionproject.api.scs.models.CreateSingleMatch
import com.example.streetchampionproject.api.scs.models.UserTeamRole
import com.example.streetchampionproject.common.domain.Exceptions
import com.example.streetchampionproject.common.domain.ResponseCode
import com.example.streetchampionproject.common.domain.sharedPreference.LocalStorage
import com.example.streetchampionproject.creating.createMatch.data.interfaces.CreateMatchRepository
import com.example.streetchampionproject.creating.createMatch.data.network.CreateMatchService
import io.reactivex.Completable
import io.reactivex.Single
import java.net.UnknownHostException
import javax.inject.Inject

class CreateMatchRepositoryImpl @Inject constructor(
    private val createMatchService: CreateMatchService,
    private val localStorage: LocalStorage
) : CreateMatchRepository {

    var userId = localStorage.readMessage("userId")?.toInt() ?: 0

    override fun createCommandMatch(createCommandMatch: CreateCommandMatch): Completable {
        createCommandMatch.creatorId = userId
        return createMatchService.createCommandMatch(createCommandMatch)
            .onErrorResumeNext { error ->
                when (error) {
                    is UnknownHostException -> Completable.error(Exceptions.error(ResponseCode.INTERNET_ERROR))
                    else -> Completable.error(Exceptions.error(ResponseCode.SERVER_ERROR))
                }
            }
    }

    override fun createSingleMatch(createSingleMatch: CreateSingleMatch): Completable {
        createSingleMatch.creatorId = userId
        return createMatchService.createSingleMatch(createSingleMatch)
            .onErrorResumeNext { error ->
                when (error) {
                    is UnknownHostException -> Completable.error(Exceptions.error(ResponseCode.INTERNET_ERROR))
                    else -> Completable.error(Exceptions.error(ResponseCode.SERVER_ERROR))
                }
            }
    }

    override fun determineUserStatus(): Single<UserTeamRole> =
        createMatchService.determineUserStatus(userId)
            .onErrorResumeNext { error ->
                when (error) {
                    is UnknownHostException -> Single.error(Exceptions.error(ResponseCode.INTERNET_ERROR))
                    else -> Single.error(Exceptions.error(ResponseCode.SERVER_ERROR))
                }
            }

}
