package com.example.streetchampionproject.creating.createTeam.data

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.api.scs.models.CreateTeam
import com.example.streetchampionproject.common.domain.sharedPreference.LocalStorage
import com.example.streetchampionproject.creating.createTeam.data.interfaces.CreateTeamRepository
import io.reactivex.Completable
import javax.inject.Inject

class CreateTeamRepositoryImpl @Inject constructor(
    private val streetChampionService: StreetChampionService,
    private val localStorage: LocalStorage
) : CreateTeamRepository {

    override fun createTeam(teamName: String, teamCity: String): Completable =
        streetChampionService.createTeam(
            CreateTeam(
                teamName,
                teamCity,
                localStorage.readMessage("userId")?.toInt()?: 0
            )
        )
}
