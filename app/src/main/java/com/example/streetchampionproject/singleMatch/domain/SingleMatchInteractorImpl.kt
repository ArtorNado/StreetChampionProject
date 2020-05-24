package com.example.streetchampionproject.singleMatch.domain

import com.example.streetchampionproject.api.scs.models.MatchSingleDetailInfo
import com.example.streetchampionproject.api.scs.models.UserStatusInPlace
import com.example.streetchampionproject.singleMatch.data.interfaces.SingleMatchRepository
import com.example.streetchampionproject.singleMatch.domain.interfaces.SingleMatchInteractor
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class SingleMatchInteractorImpl @Inject constructor(
    private val singleMatchRepository: SingleMatchRepository
) : SingleMatchInteractor {

    override fun getSingleMatch(matchId: Int): Observable<MatchSingleDetailInfo> =
        singleMatchRepository.getSingleMatchInfoLocal(matchId)

    override fun updateSingleMatch(matchId: Int): Completable =
        singleMatchRepository.updateSingleMatchInfo(matchId)

    override fun getUserStatusInMatch(matchId: Int): Observable<UserStatusInPlace> =
        singleMatchRepository.getUserStatusInMatchLocal(matchId)

    override fun updateUserStatusInMatch(matchId: Int): Completable =
        singleMatchRepository.updateUserStatusInMatch(matchId)

    override fun joinInMatch(matchSingle: MatchSingleDetailInfo?): Completable {
        return if (matchSingle?.currentNumberParticipant != matchSingle?.numberParticipant)
            singleMatchRepository.joinInMatch(
                matchSingle?.matchId ?: 0
            ) else Completable.error(IllegalArgumentException("Maximum number of players"))
    }

}
