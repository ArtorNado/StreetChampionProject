package com.example.streetchampionproject.clubPage.domain

import com.example.streetchampionproject.api.scs.response.NotificationForSend
import com.example.streetchampionproject.api.scs.response.StreetChampionResponse
import com.example.streetchampionproject.api.scs.response.UserStatusInTeam
import com.example.streetchampionproject.clubPage.data.interfaces.ClubPageRepository
import com.example.streetchampionproject.clubPage.domain.interfaces.ClubPageInteractor
import com.example.streetchampionproject.common.sharedPreference.LocalStorage
import io.reactivex.Single
import javax.inject.Inject

class ClubPageInteractorImpl @Inject constructor(
    private val clubPageRepository: ClubPageRepository,
    private val localStorage: LocalStorage
) : ClubPageInteractor {

    override fun getTeam(id: Int) = clubPageRepository.getTeam(id)

    override fun determineUserStatusInTeam(teamId: Int): Single<UserStatusInTeam> =
        clubPageRepository.getUserStatusInTeam(
            localStorage.readMessage("userId")?.toInt() ?: 0,
            teamId
        )

    override fun sendNotif(teamId: Int): Single<StreetChampionResponse> =
        clubPageRepository.sendNotif(NotificationForSend(0,
            localStorage.readMessage("userId")?.toInt() ?: 0, teamId,
            NOTIFICATION_TYPE, NOTIFICATION_STATUS))


    companion object{
        private const val NOTIFICATION_STATUS = 3
        private const val NOTIFICATION_TYPE = 1
    }
}
