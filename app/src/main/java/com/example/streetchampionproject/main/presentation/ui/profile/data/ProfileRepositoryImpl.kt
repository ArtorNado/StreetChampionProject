package com.example.streetchampionproject.main.presentation.ui.profile.data

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.api.scs.models.UserData
import com.example.streetchampionproject.common.data.databse.dao.UserDataDao
import com.example.streetchampionproject.common.data.databse.models.UserDataEntity
import com.example.streetchampionproject.main.presentation.ui.profile.data.interfaces.ProfileRepository
import com.example.streetchampionproject.main.presentation.ui.profile.data.mappers.mapUserDataEntityToUserData
import com.example.streetchampionproject.main.presentation.ui.profile.data.mappers.mapUserDataRemoteToUserDataEntity
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val streetChampionService: StreetChampionService,
    private val userDataDao: UserDataDao
) :
    ProfileRepository {

    override fun setUserDataLocal(userDataEntity: UserDataEntity) {
        userDataDao.setUserData(userDataEntity)
    }

    override fun getUserDataLocal(userId: Int): Observable<UserData> {
        return userDataDao.getUserData(userId)
            .map {
                mapUserDataEntityToUserData(it)
            }
    }

    override fun updateUserData(userId: Int): Completable {
        return streetChampionService.getUser(userId)
            .map { mapUserDataRemoteToUserDataEntity(it) }
            .doOnSuccess { setUserDataLocal(it) }
            .ignoreElement()
    }
}
