package com.example.streetchampionproject.main.presentation.ui.profile.data.mappers

import com.example.streetchampionproject.api.scs.models.UserData
import com.example.streetchampionproject.common.data.databse.models.UserDataEntity
import com.example.streetchampionproject.main.presentation.ui.profile.data.model.UserDataRemote

fun mapUserDataRemoteToUserData(userDataRemote: UserDataRemote): UserData {
    return with(userDataRemote) {
        UserData(
            userId,
            userFirstName,
            userSecondName,
            userGender,
            userCity,
            team?.teamName ?: "Dont have team"
        )
    }
}

fun mapUserDataToUserDataEntity(userData: UserData): UserDataEntity {
    return with(userData) {
        UserDataEntity(
            userId,
            userFirstName,
            userSecondName,
            userGender,
            userCity,
            team
        )
    }
}

fun mapUserDataEntityToUserData(userDataEntity: UserDataEntity): UserData {
    return with(userDataEntity) {
        UserData(
            userId,
            userFirstName,
            userSecondName,
            userGender, userCity,
            team
        )
    }
}

fun mapUserDataRemoteToUserDataEntity(userDataRemote: UserDataRemote): UserDataEntity {
    return with(userDataRemote) {
        UserDataEntity(
            userId,
            userFirstName,
            userSecondName,
            userGender,
            userCity,
            team?.teamName ?: "Dont haveTeam"
        )
    }
}

