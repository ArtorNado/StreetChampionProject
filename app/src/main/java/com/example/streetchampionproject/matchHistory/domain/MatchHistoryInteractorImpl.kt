package com.example.streetchampionproject.matchHistory.domain

import com.example.streetchampionproject.matchHistory.data.interfaces.MatchHistoryRepository
import com.example.streetchampionproject.matchHistory.domain.interfaces.MatchHistoryInteractor
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class MatchHistoryInteractorImpl @Inject constructor(
    private val matchHistoryRepository: MatchHistoryRepository
) : MatchHistoryInteractor {

    override fun getEndedCommandMatchLocal(matchType: String, teamId: Int): Observable<List<Any?>> {
        return when (matchType) {
            "Ended" -> matchHistoryRepository.getEndedMatchListLocal(teamId)
            "Feature" -> matchHistoryRepository.getFeatureMatchListLocal(teamId)
            else -> Observable.error(IllegalArgumentException("Выберите тип матча"))
        }
    }

    override fun updateEndedCommandMatch(matchType: String, teamId: Int): Completable {
        return when (matchType) {
            "Ended" -> matchHistoryRepository.updateEndedMatchList(teamId)
            "Feature" -> matchHistoryRepository.updateFeatureMatchList(teamId)
            else -> Completable.error(IllegalArgumentException("Выберите тип матча"))
        }

    }
}
