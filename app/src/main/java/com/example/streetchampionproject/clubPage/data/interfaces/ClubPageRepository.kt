package com.example.streetchampionproject.clubPage.data.interfaces

import com.example.streetchampionproject.api.scs.models.NotificationForSend
import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.api.scs.models.UserStatusInTeam
import io.reactivex.Completable
import io.reactivex.Observable

interface ClubPageRepository{

    fun getTeamLocal(id: Int): Observable<Teams>

    fun updateTeam(id: Int): Completable

    fun sendNotif(notification: NotificationForSend): Completable

    fun getUserStatus(teamId: Int): Observable<UserStatusInTeam>

    fun updateUserStatus(teamId: Int): Completable
}
