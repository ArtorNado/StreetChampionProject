package com.example.streetchampionproject.commandMatch.data

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.api.scs.models.MatchCommand
import com.example.streetchampionproject.api.scs.models.UserTeamRole
import com.example.streetchampionproject.commandMatch.data.interfaces.CommandMatchRepository
import com.example.streetchampionproject.commandMatch.data.mappers.mapCommandMatchEntityToLocal
import com.example.streetchampionproject.commandMatch.data.mappers.mapCommandMatchRemoteToEntity
import com.example.streetchampionproject.common.data.databse.dao.MatchCommandDao
import com.example.streetchampionproject.common.data.databse.models.MatchCommandEntity
import com.example.streetchampionproject.common.domain.sharedPreference.LocalStorage
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class CommandMatchRepositoryImpl @Inject constructor(
    private val streetChampionService: StreetChampionService,
    private val matchCommandDao: MatchCommandDao,
    private val localStorage: LocalStorage
) : CommandMatchRepository {

    override fun getCommandMatchLocal(matchId: Int): Observable<MatchCommand> =
        matchCommandDao.getCommandMatch(matchId)
            .map { mapCommandMatchEntityToLocal(it) }

    override fun updateCommandMatchLocal(matchId: Int): Completable =
        streetChampionService.getCommandMatch(matchId)
            .map { setCommandMatchLocal(mapCommandMatchRemoteToEntity(it)) }
            .ignoreElement()

    override fun getUserStatus(): Single<UserTeamRole> =
        streetChampionService.determineUserStatus(localStorage.readMessage("userId")?.toInt() ?: 0)

    override fun endCommandMatch(
        matchId: Int,
        firstTeamScore: Int,
        secondTeamScore: Int
    ): Completable =
        streetChampionService.endCommandMatch(matchId, firstTeamScore, secondTeamScore)


    private fun setCommandMatchLocal(matchCommandEntity: MatchCommandEntity) {
        matchCommandDao.setCommandMatch(matchCommandEntity)
    }
}
