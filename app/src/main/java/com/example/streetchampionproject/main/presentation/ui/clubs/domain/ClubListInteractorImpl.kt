package com.example.streetchampionproject.main.presentation.ui.clubs.domain

import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.main.presentation.ui.clubs.data.interfaces.ClubListRepository
import com.example.streetchampionproject.main.presentation.ui.clubs.domain.interfaces.ClubListInteractor
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ClubListInteractorImpl @Inject constructor(
    private val clubListRepository: ClubListRepository
) : ClubListInteractor {

    override fun getAllTeams(): Observable<List<Teams>> = clubListRepository.getTeamsLocal()


    override fun getTeamsByCity(city: String): Observable<List<Teams>> {
        if (city.isEmpty()) return Observable.error(IllegalArgumentException("Empty query"))
        return clubListRepository.getTeamsByCityLocal(city)
    }

    override fun updateTeamsList(): Completable =
        clubListRepository.updateTeams()

    override fun updateTeamsListByCity(city: String): Completable =
        clubListRepository.updateTeamsByCity(city)

}
