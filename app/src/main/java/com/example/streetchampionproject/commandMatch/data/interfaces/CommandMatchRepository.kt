package com.example.streetchampionproject.commandMatch.data.interfaces

import com.example.streetchampionproject.api.scs.models.MatchCommand
import com.example.streetchampionproject.api.scs.models.UserTeamRole
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface CommandMatchRepository {

    fun getCommandMatchLocal(matchId: Int): Observable<MatchCommand>

    fun updateCommandMatchLocal(matchId: Int): Completable

    fun getUserStatus(): Single<UserTeamRole>

    fun endCommandMatch(matchId: Int, firstTeamScore: Int, secondTeamScore: Int): Completable

    fun joinCommandMatch(matchId: Int): Completable
}
