package com.example.streetchampionproject.commandMatch.domain

import com.example.streetchampionproject.api.scs.models.MatchCommand
import com.example.streetchampionproject.api.scs.models.UserTeamRole
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface CommandMatchInteractor {

    fun getCommandMatch(matchId: Int): Observable<MatchCommand>

    fun updateCommandMatch(matchId: Int): Completable

    fun determineUserStatus(): Single<UserTeamRole>

    fun endCommandMatch(matchId: Int, firstTeamScore: Int, secondTeamScore: Int): Completable
}
