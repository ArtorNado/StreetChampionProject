package com.example.streetchampionproject.creating.createMatch.domain.interfaces

import com.example.streetchampionproject.api.scs.models.CreateCommandMatch
import com.example.streetchampionproject.api.scs.models.CreateSingleMatch
import com.example.streetchampionproject.api.scs.models.UserTeamRole
import io.reactivex.Completable
import io.reactivex.Single

interface CreateMatchInteractor {

    fun createSingleMatch(createSingleMatch: CreateSingleMatch): Completable

    fun createCommandMatch(createCommandMatch: CreateCommandMatch): Completable

    fun determineUserRole(): Single<UserTeamRole>
}
