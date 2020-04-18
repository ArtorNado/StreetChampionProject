package com.example.streetchampionproject.clubPage.presentation.ui.squad.data

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.api.scs.response.Players
import com.example.streetchampionproject.clubPage.presentation.ui.squad.data.interfaces.SquadRepository
import io.reactivex.Single
import javax.inject.Inject

class SquadRepositoryImpl @Inject constructor(
    private val streetChampionService: StreetChampionService
): SquadRepository {

    override fun getPlayers(teamId: Int): Single<List<Players>> = streetChampionService.getPlayers(teamId)

}
