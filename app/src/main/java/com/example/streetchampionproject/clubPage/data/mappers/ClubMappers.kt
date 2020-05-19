package com.example.streetchampionproject.clubPage.data.mappers

import com.example.streetchampionproject.api.scs.models.NotificationForSend
import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.api.scs.models.UserStatusInPlace
import com.example.streetchampionproject.clubPage.data.model.NotificationRemote
import com.example.streetchampionproject.clubPage.data.model.TeamRemote
import com.example.streetchampionproject.clubPage.data.model.UserStatusInPlaceRemote
import com.example.streetchampionproject.common.data.databse.models.TeamsEntity
import com.example.streetchampionproject.common.data.databse.models.UserStatusInPlaceEntity

fun mapTeamsEntityToTeams(teamsEntity: TeamsEntity): Teams {
    return with(teamsEntity) {
        Teams(
            teamId,
            teamName,
            teamCity,
            creatorId
        )
    }
}

fun mapTeamsRemoteToTeamsEntity(teamsRemote: TeamRemote): TeamsEntity {
    return with(teamsRemote) {
        TeamsEntity(
            teamId,
            teamName,
            teamCity,
            creatorId
        )
    }
}

fun mapUserStatusEntityToUserStatusInTeam(
    userStatusInPlaceEntity: UserStatusInPlaceEntity
): UserStatusInPlace {
    return with(userStatusInPlaceEntity) {
        UserStatusInPlace(userStatus)
    }
}

fun mapUserStatusRemoteToUserStatusEntity(
    userStatusInPlaceRemote: UserStatusInPlaceRemote, userId: Int, teamId: Int
):
        UserStatusInPlaceEntity =
    UserStatusInPlaceEntity(userId, teamId, userStatusInPlaceRemote.status)


fun mapNotificationFsToNotificationRemote(
    notificationForSend: NotificationForSend,
    senderId: Int
): NotificationRemote {
    return with(notificationForSend) {
        NotificationRemote(
            notificationId,
            senderId,
            recipientId,
            notificationType,
            notificationStatus
        )
    }
}

