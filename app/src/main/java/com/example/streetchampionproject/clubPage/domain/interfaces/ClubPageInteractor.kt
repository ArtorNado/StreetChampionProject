package com.example.streetchampionproject.clubPage.domain.interfaces

import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.api.scs.models.UserStatusInPlace
import io.reactivex.Completable
import io.reactivex.Observable

interface ClubPageInteractor {

    fun getTeamLocal(id: Int): Observable<Teams>

    fun updateTeam(id: Int): Completable

    fun getUserStatusInTeam(teamId: Int): Observable<UserStatusInPlace>

    fun updateUserStatusInTeam(teamId: Int): Completable

    fun sendNotif(teamId: Int): Completable
}
