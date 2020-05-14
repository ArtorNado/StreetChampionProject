package com.example.streetchampionproject.clubPage.domain

import com.example.streetchampionproject.api.scs.models.NotificationForSend
import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.api.scs.models.UserStatusInTeam
import com.example.streetchampionproject.clubPage.data.interfaces.ClubPageRepository
import com.example.streetchampionproject.clubPage.domain.interfaces.ClubPageInteractor
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ClubPageInteractorImpl @Inject constructor(
    private val clubPageRepository: ClubPageRepository
) : ClubPageInteractor {

    override fun getTeamLocal(id: Int): Observable<Teams> = clubPageRepository.getTeamLocal(id)

    override fun updateTeam(id: Int): Completable = clubPageRepository.updateTeam(id)

    override fun getUserStatusInTeam(teamId: Int): Observable<UserStatusInTeam> =
        clubPageRepository.getUserStatus(teamId)

    override fun updateUserStatusInTeam(teamId: Int): Completable =
        clubPageRepository.updateUserStatus(teamId)

    override fun sendNotif(teamId: Int): Completable =
        clubPageRepository.sendNotif(
            NotificationForSend(
                0,
                teamId,
                NOTIFICATION_TYPE, NOTIFICATION_STATUS
            )
        )


    companion object {
        private const val NOTIFICATION_STATUS = 3
        private const val NOTIFICATION_TYPE = 1
    }
}
