package com.example.streetchampionproject.clubPage.presentation.ui.overview.domain

import com.example.streetchampionproject.clubPage.presentation.ui.overview.data.interfaces.OverviewRepository
import com.example.streetchampionproject.clubPage.presentation.ui.overview.domain.interfaces.OverviewInteractor
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class OverviewInteractorImpl @Inject constructor(
    private val overviewRepository: OverviewRepository
) : OverviewInteractor {

    override fun getEndedCommandMatchLocal(matchType: String, teamId: Int): Observable<List<Any?>> {
        return when (matchType) {
            "Ended" -> overviewRepository.getEndedMatchListLocal(teamId)
            "Feature" -> overviewRepository.getFeatureMatchListLocal(teamId)
            else -> Observable.error(IllegalArgumentException("Выберите тип матча"))
        }
    }

    override fun updateEndedCommandMatch(matchType: String, teamId: Int): Completable {
        return when (matchType) {
            "Ended" -> overviewRepository.updateEndedMatchList(teamId)
            "Feature" -> overviewRepository.updateFeatureMatchList(teamId)
            else -> Completable.error(IllegalArgumentException("Выберите тип матча"))
        }

    }
}
