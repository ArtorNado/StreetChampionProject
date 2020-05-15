package com.example.streetchampionproject.creating.createTeam.domain

import com.example.streetchampionproject.creating.createTeam.data.interfaces.CreateTeamRepository
import com.example.streetchampionproject.creating.createTeam.domain.interfaces.CreateTeamInteractor
import io.reactivex.Completable
import javax.inject.Inject

class CreateTeamInteractorImpl @Inject constructor(
    private val createTeamRepository: CreateTeamRepository
) : CreateTeamInteractor {

    override fun createTeam(teamName: String, teamCity: String): Completable =
        createTeamRepository.createTeam(teamName, teamCity)

}
