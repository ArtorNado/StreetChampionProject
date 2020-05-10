package com.example.streetchampionproject.main.presentation.ui.clubs.domain.interfaces

import com.example.streetchampionproject.api.scs.models.Teams
import io.reactivex.Single

interface ClubListInteractor {

    fun getTeams(): Single<List<Teams>>

    fun getTeamsByCity(city: String): Single<List<Teams>>

    fun getTeamsByName(name: String): Single<List<Teams>>

}
