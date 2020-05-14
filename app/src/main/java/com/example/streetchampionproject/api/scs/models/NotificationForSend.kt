package com.example.streetchampionproject.api.scs.models

data class NotificationForSend(
    var notificationId: Int,
    var recipientId: Int,
    var notificationType: Int,
    var notificationStatus: Int
)
