package com.example.streetchampionproject.clubPage.data

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.api.scs.response.NotificationForSend
import com.example.streetchampionproject.api.scs.response.StreetChampionResponse
import com.example.streetchampionproject.api.scs.response.UserStatusInTeam
import com.example.streetchampionproject.clubPage.data.interfaces.ClubPageRepository
import io.reactivex.Single
import javax.inject.Inject

class ClubPageRepositoryImpl @Inject constructor(
    private var streetChampionService: StreetChampionService
): ClubPageRepository {

    override fun getTeam(id: Int) = streetChampionService.getTeam(id)

    override fun getUserStatusInTeam(userId: Int, teamId: Int): Single<UserStatusInTeam> =
        streetChampionService.getUserStatusInTeam(userId, teamId)

    override fun sendNotif(notification: NotificationForSend): Single<StreetChampionResponse> =
        streetChampionService.sendNotification(notification)
}
