package com.example.streetchampionproject.clubPage.data.interfaces

import com.example.streetchampionproject.api.scs.response.NotificationForSend
import com.example.streetchampionproject.api.scs.response.StreetChampionResponse
import com.example.streetchampionproject.api.scs.response.Teams
import com.example.streetchampionproject.api.scs.response.UserStatusInTeam
import io.reactivex.Single

interface ClubPageRepository{

    fun getTeam(id: Int): Single<Teams>

    fun getUserStatusInTeam(userId: Int, teamId: Int): Single<UserStatusInTeam>

    fun sendNotif(notification: NotificationForSend): Single<StreetChampionResponse>
}
