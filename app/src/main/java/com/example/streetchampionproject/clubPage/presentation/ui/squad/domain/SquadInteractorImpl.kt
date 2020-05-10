package com.example.streetchampionproject.clubPage.presentation.ui.squad.domain

import com.example.streetchampionproject.api.scs.models.Players
import com.example.streetchampionproject.clubPage.presentation.ui.squad.data.interfaces.SquadRepository
import com.example.streetchampionproject.clubPage.presentation.ui.squad.domain.interfaces.SquadInteractor
import io.reactivex.Single
import javax.inject.Inject

class SquadInteractorImpl @Inject constructor(
    private val squadRepository: SquadRepository
): SquadInteractor {
    override fun getPlayers(teamId: Int): Single<List<Players>> = squadRepository.getPlayers(teamId)
}
