package com.example.streetchampionproject.clubPage.presentation.ui.overview.data

import com.example.streetchampionproject.clubPage.presentation.ui.overview.data.interfaces.OverviewRepository
import com.example.streetchampionproject.clubPage.presentation.ui.overview.data.mappers.mapEndedEntityToLocal
import com.example.streetchampionproject.clubPage.presentation.ui.overview.data.mappers.mapEndedRemoteToEntity
import com.example.streetchampionproject.clubPage.presentation.ui.overview.data.network.OverviewService
import com.example.streetchampionproject.common.data.databse.dao.EndedCommandMatchDao
import com.example.streetchampionproject.common.data.databse.dao.MatchCommandDao
import com.example.streetchampionproject.common.data.databse.models.EndedCommandMatchEntity
import com.example.streetchampionproject.common.data.databse.models.MatchCommandEntity
import com.example.streetchampionproject.match.data.mappers.mapMatchEntityToCommand
import com.example.streetchampionproject.match.data.mappers.mapMatchRemoteToEntity
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class OverviewRepositoryImpl @Inject constructor(
    private val overviewService: OverviewService,
    private val endedCommandMatchDao: EndedCommandMatchDao,
    private val commandMatchDao: MatchCommandDao
) : OverviewRepository {

    override fun getEndedMatchListLocal(teamId: Int): Observable<List<Any?>> =
        endedCommandMatchDao.getEndedMatch(teamId)
            .map { mapEndedEntityToLocal(it) }


    override fun updateEndedMatchList(teamId: Int): Completable =
        overviewService.getEndedCommandMatches(teamId)
            .doOnSuccess { commandMatchDao.clear() }
            .map { setEndedCommandMatchLocal(mapEndedRemoteToEntity(it)) }
            .ignoreElement()

    override fun getFeatureMatchListLocal(teamId: Int): Observable<List<Any?>> =
        commandMatchDao.getCommandMatches(teamId)
            .map { mapMatchEntityToCommand(it) }

    override fun updateFeatureMatchList(teamId: Int): Completable =
        overviewService.getFeatureCommandMatches(teamId)
            .doOnSuccess { commandMatchDao.clear() }
            .map { setFeatureCommandMatchLocal(mapMatchRemoteToEntity(it, "Undefined")) }
            .ignoreElement()


    private fun setEndedCommandMatchLocal(endedCommandMatchEntity: List<EndedCommandMatchEntity>) {
        endedCommandMatchDao.setEndedMatch(endedCommandMatchEntity)
    }

    private fun setFeatureCommandMatchLocal(matchCommandEntity: List<MatchCommandEntity>){
        commandMatchDao.setCommandMatchList(matchCommandEntity)
    }


}
