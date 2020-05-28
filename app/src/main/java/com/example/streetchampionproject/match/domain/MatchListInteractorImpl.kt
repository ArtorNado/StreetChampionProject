package com.example.streetchampionproject.match.domain

import com.example.streetchampionproject.common.domain.Exceptions
import com.example.streetchampionproject.common.domain.ResponseCode
import com.example.streetchampionproject.match.data.interfaces.MatchListRepository
import com.example.streetchampionproject.match.domain.interfaces.MatchListInteractor
import io.reactivex.Single
import javax.inject.Inject

class MatchListInteractorImpl @Inject constructor(
    private val matchListRepository: MatchListRepository
) : MatchListInteractor {

    override fun getMatchList(matchType: String, role: String, city: String): Single<List<Any?>> {
        return if (city == "Undefined") {
            when (matchType) {
                TYPE_SINGLE -> matchListRepository.getSingleMatchLocal(role)
                TYPE_COMMAND -> matchListRepository.getCommandMatchLocal(role)
                else -> Single.error(Exceptions.error(ResponseCode.MATCH_TYPE_ERROR))
            }
        } else {
            when (matchType) {
                TYPE_SINGLE -> matchListRepository.getSingleMatchLocal(role)
                TYPE_COMMAND -> matchListRepository.getCommandMatchByCityLocal(city)
                else -> Single.error(Exceptions.error(ResponseCode.MATCH_TYPE_ERROR))
            }
        }
    }

    override fun updateMatchList(
        matchType: String,
        role: String,
        city: String
    ): Single<List<Any?>> {
        if (city == "Undefined") {
            return when (matchType) {
                TYPE_SINGLE -> {
                    when (role) {
                        CHIP_NOT_CHECKED -> matchListRepository.updateSingleMatchWithoutRole()
                        ROLE_ADMIN, ROLE_PARTICIPANT, ROLE_FREE -> matchListRepository.updateSingleMatchListWithRole(
                            role
                        )
                        else -> matchListRepository.updateSingleMatchListWithRole(role)
                    }
                }
                TYPE_COMMAND -> {
                    when (role) {
                        CHIP_NOT_CHECKED -> matchListRepository.updateCommandMatchWithoutRole()
                        ROLE_ADMIN, ROLE_PARTICIPANT, ROLE_FREE -> matchListRepository.updateCommandMatchWithRole(
                            role
                        )
                        else -> matchListRepository.updateCommandMatchWithRole(role)
                    }
                }
                else -> Single.error(Exceptions.error(ResponseCode.MATCH_TYPE_ERROR))
            }
        } else {
            return when (matchType) {
                TYPE_SINGLE -> {
                    when (role) {
                        CHIP_NOT_CHECKED -> matchListRepository.updateSingleMatchByCity(city)
                        ROLE_ADMIN, ROLE_PARTICIPANT, ROLE_FREE -> matchListRepository.updateSingleMatchByRoleAndCity(
                            role,
                            city
                        )
                        else -> matchListRepository.updateSingleMatchListWithRole(role)
                    }
                }
                TYPE_COMMAND -> {
                    when (role) {
                        CHIP_NOT_CHECKED -> matchListRepository.updateCommandMatchByCity(city)
                        ROLE_ADMIN, ROLE_PARTICIPANT, ROLE_FREE -> matchListRepository.updateCommandMatchByRoleAndCity(
                            role,
                            city
                        )
                        else -> matchListRepository.updateCommandMatchWithRole(role)
                    }
                }
                else -> Single.error(Exceptions.error(ResponseCode.MATCH_TYPE_ERROR))
            }
        }
    }


    companion object {
        const val CHIP_NOT_CHECKED = "Undefined"
        const val TYPE_SINGLE = "Single"
        const val TYPE_COMMAND = "Command"
        const val ROLE_ADMIN = "Admin"
        const val ROLE_FREE = "Free"
        const val ROLE_PARTICIPANT = "Participant"
    }

}
