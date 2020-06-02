package com.example.streetchampionproject.creating.createTeam.data

import com.example.streetchampionproject.api.scs.models.CreateTeam
import com.example.streetchampionproject.common.domain.Exceptions
import com.example.streetchampionproject.common.domain.ResponseCode
import com.example.streetchampionproject.common.domain.sharedPreference.LocalStorage
import com.example.streetchampionproject.creating.createTeam.data.interfaces.CreateTeamRepository
import com.example.streetchampionproject.creating.createTeam.data.network.CreateTeamService
import io.reactivex.Completable
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class CreateTeamRepositoryImpl @Inject constructor(
    private val createTeamService: CreateTeamService,
    private val localStorage: LocalStorage
) : CreateTeamRepository {

    override fun createTeam(teamName: String, teamCity: String): Completable =
        createTeamService.createTeam(
            CreateTeam(
                teamName,
                teamCity,
                localStorage.readMessage("userId")?.toInt() ?: 0
            )
        )
            .onErrorResumeNext {
                when (it) {
                    is UnknownHostException -> Completable.error(Exceptions.error(ResponseCode.INTERNET_ERROR))
                    is HttpException -> {
                        when (it.message.toString()) {
                            "HTTP 403 " -> Completable.error(
                                Exceptions.error(
                                    ResponseCode.USER_ALREADY_HAVE_TEAM
                                )
                            )
                            "HTTP 500 " -> Completable.error(
                                Exceptions.error(
                                    ResponseCode.TEAM_NAME_ALREADY_EXIST
                                )
                            )
                            else -> {
                                Completable.error(Exceptions.error(ResponseCode.SERVER_ERROR))
                            }
                        }
                    }
                    else -> Completable.error(Exceptions.error(ResponseCode.SERVER_ERROR))
                }
            }

}
