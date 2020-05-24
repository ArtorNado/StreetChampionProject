package com.example.streetchampionproject.creating.createMatch.data

import android.util.Log
import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.api.scs.models.CreateCommandMatch
import com.example.streetchampionproject.api.scs.models.CreateSingleMatch
import com.example.streetchampionproject.api.scs.models.UserTeamRole
import com.example.streetchampionproject.common.domain.sharedPreference.LocalStorage
import com.example.streetchampionproject.creating.createMatch.data.interfaces.CreateMatchRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class CreateMatchRepositoryImpl @Inject constructor(
    private val streetChampionService: StreetChampionService,
    private val localStorage: LocalStorage
) : CreateMatchRepository {

    var userId = localStorage.readMessage("userId")?.toInt() ?: 0

    override fun createCommandMatch(createCommandMatch: CreateCommandMatch): Completable {
        createCommandMatch.creatorId = userId
        return streetChampionService.createCommandMatch(createCommandMatch)
    }

    override fun createSingleMatch(createSingleMatch: CreateSingleMatch): Completable {
        createSingleMatch.creatorId =
        return streetChampionService.createSingleMatch(createSingleMatch)
    }

    override fun determineUserStatus(): Single<UserTeamRole> {
        Log.e("STARTTTTTTTT", "STARRTASDTATSDASD")
        return streetChampionService.determineUserStatus(userId)
    }
}
