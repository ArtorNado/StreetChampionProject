package com.example.streetchampionproject.match.domain

import com.example.streetchampionproject.match.data.interfaces.MatchListRepository
import com.example.streetchampionproject.match.domain.interfaces.MatchListInteractor
import io.reactivex.Single
import javax.inject.Inject

class MatchListInteractorImpl @Inject constructor(
    private val matchListRepository: MatchListRepository
) : MatchListInteractor {

    override fun getMatchList(matchType: String, role: String): Single<List<Any?>> {
        return when (matchType) {
            TYPE_SINGLE -> matchListRepository.getSingleMatchLocal(role)
            TYPE_COMMAND -> matchListRepository.getCommandMatchLocal(role)
            else -> Single.error(IllegalArgumentException("You must choose match type"))
        }
    }

    override fun updateMatchList(matchType: String, role: String): Single<List<Any?>> {
        return when (matchType) {
            TYPE_SINGLE -> {
                when (role) {
                    CHIP_NOT_CHECKED -> matchListRepository.updateSingleMatchWithoutRole()
                    ROLE_ADMIN -> matchListRepository.updateSingleMatchListWithRole(role)
                    ROLE_PARTICIPANT -> matchListRepository.updateSingleMatchListWithRole(role)
                    ROLE_FREE -> matchListRepository.updateSingleMatchListWithRole(role)
                    else -> matchListRepository.updateSingleMatchListWithRole(role)
                }
            }
            TYPE_COMMAND -> {
                when (role) {
                    CHIP_NOT_CHECKED -> matchListRepository.updateCommandMatchWithoutRole()
                    ROLE_ADMIN -> matchListRepository.updateCommandMatchWithRole(role)
                    ROLE_PARTICIPANT -> matchListRepository.updateCommandMatchWithRole(role)
                    ROLE_FREE -> matchListRepository.updateCommandMatchWithRole(role)
                    else -> matchListRepository.updateSingleMatchListWithRole(role)
                }
            }
            else -> Single.error(java.lang.IllegalArgumentException("Server error"))
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
