package com.example.streetchampionproject.clubPage.domain.interfaces

import com.example.streetchampionproject.api.scs.models.StreetChampionResponse
import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.api.scs.models.UserStatusInTeam
import io.reactivex.Single

interface ClubPageInteractor{

    fun getTeam(id: Int): Single<Teams>

    fun determineUserStatusInTeam(teamId: Int): Single<UserStatusInTeam>

    fun sendNotif(teamId: Int): Single<StreetChampionResponse>
}
