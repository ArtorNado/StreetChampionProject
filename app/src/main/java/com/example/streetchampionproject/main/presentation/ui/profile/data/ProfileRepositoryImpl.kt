package com.example.streetchampionproject.main.presentation.ui.profile.data

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.api.scs.models.UserData
import com.example.streetchampionproject.common.database.userData.UserDataDao
import com.example.streetchampionproject.common.database.userData.UserDataEntity
import com.example.streetchampionproject.main.presentation.ui.profile.data.interfaces.ProfileRepository
import io.reactivex.Single
import java.net.UnknownHostException
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val streetChampionService: StreetChampionService,
    private val userDataDao: UserDataDao
) :
    ProfileRepository {

    override fun getUserData(userId: Int): Single<UserData> =
        streetChampionService.getUser(userId)
            .map {
                setUserDataLocal(it)
            }.onErrorResumeNext {
                if (it is UnknownHostException) getUserDataLocal(userId)
                else Single.error(it)
            }

    override fun setUserDataLocal(userData: UserData): UserData {
        userDataDao.setUserData(
            UserDataEntity(
                userData.userId,
                userData.userFirstName,
                userData.userSecondName,
                userData.userGender,
                userData.userCity,
                userData.team
            )
        )
        return userData
    }

    override fun getUserDataLocal(userId: Int): Single<UserData> {
        return userDataDao.getUserData(userId)
            .map {
                UserData(
                    it.userId,
                    it.userFirstName,
                    it.userSecondName,
                    it.userGender,
                    it.userCity,
                    it.team
                )
            }.onErrorResumeNext {
                Single.error(it)
            }
    }
}
