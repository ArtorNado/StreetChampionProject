package com.example.streetchampionproject.clubPage.presentation.ui.squad.data.interfaces

import com.example.streetchampionproject.api.scs.response.Players
import io.reactivex.Single

interface SquadRepository {

    fun getPlayers(teamId: Int): Single<List<Players>>
}
