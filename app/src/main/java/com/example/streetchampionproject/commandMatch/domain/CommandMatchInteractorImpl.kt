package com.example.streetchampionproject.commandMatch.domain

import com.example.streetchampionproject.api.scs.models.MatchCommand
import com.example.streetchampionproject.api.scs.models.UserTeamRole
import com.example.streetchampionproject.commandMatch.data.interfaces.CommandMatchRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class CommandMatchInteractorImpl @Inject constructor(
    private val commandMatchRepository: CommandMatchRepository
) : CommandMatchInteractor {

    override fun getCommandMatch(matchId: Int): Observable<MatchCommand> =
        commandMatchRepository.getCommandMatchLocal(matchId)

    override fun updateCommandMatch(matchId: Int): Completable =
        commandMatchRepository.updateCommandMatchLocal(matchId)

    override fun determineUserStatus(): Single<UserTeamRole> =
        commandMatchRepository.getUserStatus()

    override fun endCommandMatch(
        matchId: Int,
        firstTeamScore: Int,
        secondTeamScore: Int
    ): Completable =
        commandMatchRepository.endCommandMatch(matchId, firstTeamScore, secondTeamScore)

    override fun joinCommandMatch(matchId: Int): Completable =
        commandMatchRepository.joinCommandMatch(matchId)


}
