package com.example.streetchampionproject.singleMatch.domain

import com.example.streetchampionproject.api.scs.models.MatchSingleDetailInfo
import com.example.streetchampionproject.api.scs.models.UserStatusInPlace
import com.example.streetchampionproject.common.domain.Exceptions
import com.example.streetchampionproject.common.domain.ResponseCode
import com.example.streetchampionproject.singleMatch.data.interfaces.SingleMatchRepository
import com.example.streetchampionproject.singleMatch.data.mappers.mapMatchSingleToMatchSingleEntity
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
        return if (matchSingle?.currentNumberParticipant != matchSingle?.numberParticipant) {
            singleMatchRepository.joinInMatch(
                matchSingle?.matchId ?: 0
            )
                .andThen(singleMatchRepository.updateParticipants(matchSingle?.matchId ?: 0))
                .ignoreElement()
                .doOnComplete {
                    if (matchSingle != null) {
                        matchSingle.currentNumberParticipant =
                            matchSingle.currentNumberParticipant.plus(1)
                        singleMatchRepository.setSingleMatchLocal(
                            mapMatchSingleToMatchSingleEntity(
                                matchSingle, "Participant"
                            )
                        )
                    }
                }

        } else Completable.error(Exceptions.error(ResponseCode.MAX_PLAYERS))
    }

    override fun endSingleMatch(matchId: Int): Completable =
        singleMatchRepository.endSingleMatch(matchId)

}
