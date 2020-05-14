package com.example.streetchampionproject.clubPage.data.model

data class NotificationRemote(
    var notificationId: Int,
    var senderId: Int,
    var recipientId: Int,
    var notificationType: Int,
    var notificationStatus: Int
)
