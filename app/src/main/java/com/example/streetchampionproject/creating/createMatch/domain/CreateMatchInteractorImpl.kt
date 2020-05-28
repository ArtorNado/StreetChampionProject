package com.example.streetchampionproject.creating.createMatch.domain

import com.example.streetchampionproject.api.scs.models.CreateCommandMatch
import com.example.streetchampionproject.api.scs.models.CreateSingleMatch
import com.example.streetchampionproject.api.scs.models.UserTeamRole
import com.example.streetchampionproject.common.domain.Exceptions
import com.example.streetchampionproject.common.domain.ResponseCode
import com.example.streetchampionproject.creating.createMatch.data.interfaces.CreateMatchRepository
import com.example.streetchampionproject.creating.createMatch.domain.interfaces.CreateMatchInteractor
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class CreateMatchInteractorImpl @Inject constructor(
    private val createMatchRepository: CreateMatchRepository
) : CreateMatchInteractor {

    override fun createSingleMatch(createSingleMatch: CreateSingleMatch): Completable =
        createMatchRepository.createSingleMatch(createSingleMatch)

    override fun createCommandMatch(createCommandMatch: CreateCommandMatch): Completable =
        createMatchRepository.createCommandMatch(createCommandMatch)

    override fun determineUserRole(): Single<UserTeamRole> =
        createMatchRepository.determineUserStatus()
            .map {
                if (it.status != "Admin") throw Exceptions.error(ResponseCode.USER_NOT_ADMIN)
                else it
            }

}
