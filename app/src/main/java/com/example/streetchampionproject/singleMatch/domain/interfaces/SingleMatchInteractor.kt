package com.example.streetchampionproject.singleMatch.domain.interfaces

import com.example.streetchampionproject.api.scs.models.MatchSingleDetailInfo
import com.example.streetchampionproject.api.scs.models.UserStatusInPlace
import io.reactivex.Completable
import io.reactivex.Observable

interface SingleMatchInteractor {

    fun getSingleMatch(matchId: Int): Observable<MatchSingleDetailInfo>

    fun updateSingleMatch(matchId: Int): Completable

    fun getUserStatusInMatch(matchId: Int): Observable<UserStatusInPlace>

    fun updateUserStatusInMatch(matchId: Int): Completable

    fun joinInMatch(matchSingle: MatchSingleDetailInfo?): Completable
}
