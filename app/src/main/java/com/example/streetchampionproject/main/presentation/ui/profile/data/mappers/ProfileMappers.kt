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
            team?.teamName ?: "Dont have team",
            team?.teamId?:0
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
            teamName,
            teamId
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
            teamName,
            teamId
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
            team?.teamName ?: "Dont have team",
            team?.teamId?:0
        )
    }
}

