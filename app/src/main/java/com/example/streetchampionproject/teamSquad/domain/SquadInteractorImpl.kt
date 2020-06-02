package com.example.streetchampionproject.teamSquad.domain

import com.example.streetchampionproject.api.scs.models.Players
import com.example.streetchampionproject.teamSquad.data.interfaces.SquadRepository
import com.example.streetchampionproject.teamSquad.domain.interfaces.SquadInteractor
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class SquadInteractorImpl @Inject constructor(
    private val squadRepository: SquadRepository
) : SquadInteractor {

    override fun getPlayers(teamId: Int): Observable<List<Players>> =
        squadRepository.getPlayersLocal(teamId)

    override fun updatePlayers(teamId: Int): Completable =
        squadRepository.updatePlayers(teamId)
}
