package com.example.streetchampionproject.main.presentation.ui.clubs.data

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.api.scs.response.Teams
import com.example.streetchampionproject.main.presentation.ui.clubs.data.interfaces.ClubListRepository
import io.reactivex.Single
import javax.inject.Inject

class ClubListRepositoryImpl @Inject constructor(
    private val streetChampionService: StreetChampionService
): ClubListRepository {

    override fun getTeams(): Single<List<Teams>> = streetChampionService.getTeams()

    override fun getTeamsByCity(city: String): Single<List<Teams>> = streetChampionService.getTeamsByCity(city)

    override fun getTeamsByName(name: String): Single<List<Teams>> = streetChampionService.getTeamsByName(name)
}
