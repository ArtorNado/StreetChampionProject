package com.example.streetchampionproject.creating.createTeam.data.interfaces

import io.reactivex.Completable

interface CreateTeamRepository {

    fun createTeam(teamName: String, teamCity: String): Completable
}
