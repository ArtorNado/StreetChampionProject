package com.example.streetchampionproject.clubPage.presentation.ui.squad.domain.interfaces

import com.example.streetchampionproject.api.scs.response.Players
import io.reactivex.Single

interface SquadInteractor {

    fun getPlayers(teamId: Int): Single<List<Players>>
}
