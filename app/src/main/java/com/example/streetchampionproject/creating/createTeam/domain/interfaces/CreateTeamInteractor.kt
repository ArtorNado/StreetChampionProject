package com.example.streetchampionproject.creating.createTeam.domain.interfaces

import io.reactivex.Completable

interface CreateTeamInteractor {

    fun createTeam(teamName: String, teamCity: String): Completable
}
