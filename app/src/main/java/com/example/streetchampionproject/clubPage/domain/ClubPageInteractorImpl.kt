package com.example.streetchampionproject.clubPage.domain

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

}
