package com.example.streetchampionproject.main.presentation.ui.profile.data

import com.example.streetchampionproject.api.scs.models.UserData
import com.example.streetchampionproject.common.data.databse.dao.UserDataDao
import com.example.streetchampionproject.common.data.databse.models.UserDataEntity
import com.example.streetchampionproject.common.domain.Exceptions
import com.example.streetchampionproject.common.domain.ResponseCode
import com.example.streetchampionproject.main.presentation.ui.profile.data.interfaces.ProfileRepository
import com.example.streetchampionproject.main.presentation.ui.profile.data.mappers.mapUserDataEntityToUserData
import com.example.streetchampionproject.main.presentation.ui.profile.data.mappers.mapUserDataRemoteToUserDataEntity
import com.example.streetchampionproject.main.presentation.ui.profile.data.network.ProfileService
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.HttpException
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileService: ProfileService,
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
        return profileService.getUser(userId)
            .map { mapUserDataRemoteToUserDataEntity(it) }
            .onErrorResumeNext { error ->
                when (error) {
                    is HttpException -> Single.error(Exceptions.error(ResponseCode.INTERNET_ERROR))
                    else -> Single.error(Exceptions.error(ResponseCode.SERVER_ERROR))
                }
            }
            .doOnSuccess { setUserDataLocal(it) }
            .ignoreElement()
    }
}
