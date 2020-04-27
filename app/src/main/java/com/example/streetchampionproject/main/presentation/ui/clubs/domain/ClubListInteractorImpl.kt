package com.example.streetchampionproject.main.presentation.ui.clubs.domain

import com.example.streetchampionproject.api.scs.response.Teams
import com.example.streetchampionproject.main.presentation.ui.clubs.data.interfaces.ClubListRepository
import com.example.streetchampionproject.main.presentation.ui.clubs.domain.interfaces.ClubListInteractor
import io.reactivex.Single
import javax.inject.Inject

class ClubListInteractorImpl @Inject constructor(
    private val clubListRepository: ClubListRepository
): ClubListInteractor {
    override fun getTeams(): Single<List<Teams>> = clubListRepository.getTeams()

    override fun getTeamsByCity(city: String): Single<List<Teams>> {
        if (city.isEmpty()) return Single.error(IllegalArgumentException("Empty query"))
        return clubListRepository.getTeamsByCity(city)}

    override fun getTeamsByName(name: String): Single<List<Teams>> = clubListRepository.getTeamsByName(name)
}
