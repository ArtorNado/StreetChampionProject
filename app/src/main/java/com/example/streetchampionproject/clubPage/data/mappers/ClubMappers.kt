package com.example.streetchampionproject.clubPage.data.mappers

import com.example.streetchampionproject.api.scs.models.NotificationForSend
import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.api.scs.models.UserStatusInTeam
import com.example.streetchampionproject.clubPage.data.model.NotificationRemote
import com.example.streetchampionproject.clubPage.data.model.TeamRemote
import com.example.streetchampionproject.clubPage.data.model.UserStatusInTeamRemote
import com.example.streetchampionproject.common.data.databse.models.TeamsEntity
import com.example.streetchampionproject.common.data.databse.models.UserStatusInTeamEntity

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
    userStatusInTeamEntity: UserStatusInTeamEntity
): UserStatusInTeam {
    return with(userStatusInTeamEntity) {
        UserStatusInTeam(userStatus)
    }
}

fun mapUserStatusRemoteToUserStatusEntity(
    userStatusInTeamRemote: UserStatusInTeamRemote, userId: Int, teamId: Int
):
        UserStatusInTeamEntity =
    UserStatusInTeamEntity(userId, teamId, userStatusInTeamRemote.status)


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

