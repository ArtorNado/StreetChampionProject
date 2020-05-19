package com.example.streetchampionproject.match.domain

import com.example.streetchampionproject.api.scs.models.MatchSingle
import com.example.streetchampionproject.match.data.interfaces.MatchListRepository
import com.example.streetchampionproject.match.domain.interfaces.MatchListInteractor
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class MatchListInteractorImpl @Inject constructor(
    private val matchListRepository: MatchListRepository
) : MatchListInteractor {

    override fun getMatchList(matchType: String, role: String): Observable<List<MatchSingle>> {
        return when (matchType) {
            TYPE_SINGLE -> matchListRepository.getSingleMatchLocal(role)
            else -> Observable.error(IllegalArgumentException("You must choose match type"))
        }
    }

    override fun updateMatchList(matchType: String, role: String): Completable {
        return when(role){
            CHIP_NOT_CHECKED -> matchListRepository.updateSingleMatchWithoutRole()
            else -> matchListRepository.updateSingleMatchListWithRole(role)
        }
    }


    companion object {
        const val CHIP_NOT_CHECKED = "Undefined"
        const val TYPE_SINGLE = "Single"
        const val TYPE_COMMAND = "Command"
        const val ROLE_ADMIN = "Admin"
        const val ROLE_PARTICIPANT = "Participant"
        const val CHOOSE_TYPE = "Выберите тим матча"
    }

}
