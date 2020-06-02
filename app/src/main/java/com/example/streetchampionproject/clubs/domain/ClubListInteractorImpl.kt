package com.example.streetchampionproject.clubs.domain

import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.clubs.data.interfaces.ClubListRepository
import com.example.streetchampionproject.clubs.domain.interfaces.ClubListInteractor
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class ClubListInteractorImpl @Inject constructor(
    private val clubListRepository: ClubListRepository
) : ClubListInteractor {

    override fun getAllTeams(): Observable<List<Teams>> = clubListRepository.getTeamsLocal()

    override fun getTeamsByCity(city: String): Single<List<Teams>> =
        clubListRepository.getTeamsByCityLocal(city)

    override fun updateTeamsList(): Completable =
        clubListRepository.updateTeams()

    override fun updateTeamsListByCity(city: String): Single<List<Teams>> =
        clubListRepository.updateTeamsByCity(city)

}
