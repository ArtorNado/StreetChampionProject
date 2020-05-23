package com.example.streetchampionproject.creating.createMatch.data.interfaces

import com.example.streetchampionproject.api.scs.models.CreateCommandMatch
import com.example.streetchampionproject.api.scs.models.CreateSingleMatch
import com.example.streetchampionproject.api.scs.models.UserTeamRole
import io.reactivex.Completable
import io.reactivex.Single

interface CreateMatchRepository {

    fun createCommandMatch(createCommandMatch: CreateCommandMatch): Completable

    fun createSingleMatch(createSingleMatch: CreateSingleMatch): Completable

    fun determineUserStatus(): Single<UserTeamRole>
}
