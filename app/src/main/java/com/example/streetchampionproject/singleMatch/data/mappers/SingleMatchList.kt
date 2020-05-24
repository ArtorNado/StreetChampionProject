package com.example.streetchampionproject.singleMatch.data.mappers

import com.example.streetchampionproject.api.scs.models.MatchSingleDetailInfo
import com.example.streetchampionproject.api.scs.models.UserStatusInPlace
import com.example.streetchampionproject.clubPage.data.model.UserStatusInPlaceRemote
import com.example.streetchampionproject.common.data.databse.models.MatchSingleEntity
import com.example.streetchampionproject.common.data.databse.models.UserStatusInPlaceEntity
import com.example.streetchampionproject.singleMatch.data.models.MatchSingleRemote

fun mapMatchEntityToMatchSingle(matchSingleEntity: MatchSingleEntity): MatchSingleDetailInfo =
    with(matchSingleEntity) {
        MatchSingleDetailInfo(
            matchId,
            date,
            time,
            creatorId,
            numberParticipant,
            currentNumberParticipant,
            description,
            matchCity
        )
    }

fun mapMatchSingleRemoteToMatchSingleEntity(matchSingleRemote: MatchSingleRemote): MatchSingleEntity =
    with(matchSingleRemote) {
        MatchSingleEntity(
            matchId,
            date,
            time,
            creatorId,
            numberParticipant,
            currentNumberParticipant,
            description ?: "Dont have description",
            matchCity ?: "Dont have city",
            "Undefined"
        )
    }

fun mapUserStatusEntityToUserStatusInPlace(
    userStatusInPlaceEntity: UserStatusInPlaceEntity
): UserStatusInPlace {
    return with(userStatusInPlaceEntity) {
        UserStatusInPlace(userStatus)
    }
}

fun mapUserStatusRemoteToUserStatusEntity(
    userStatusInPlaceRemote: UserStatusInPlaceRemote, userId: Int, placeId: Int
):
        UserStatusInPlaceEntity =
    UserStatusInPlaceEntity(userId, placeId, userStatusInPlaceRemote.status)
