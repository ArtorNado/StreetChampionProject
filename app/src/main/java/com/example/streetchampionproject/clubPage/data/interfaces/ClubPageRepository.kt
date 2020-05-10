package com.example.streetchampionproject.clubPage.data.interfaces

import com.example.streetchampionproject.api.scs.models.NotificationForSend
import com.example.streetchampionproject.api.scs.models.StreetChampionResponse
import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.api.scs.models.UserStatusInTeam
import io.reactivex.Single

interface ClubPageRepository{

    fun getTeam(id: Int): Single<Teams>

    fun getUserStatusInTeam(userId: Int, teamId: Int): Single<UserStatusInTeam>

    fun sendNotif(notification: NotificationForSend): Single<StreetChampionResponse>
}
