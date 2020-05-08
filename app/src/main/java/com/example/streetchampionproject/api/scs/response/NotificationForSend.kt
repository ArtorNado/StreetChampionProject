package com.example.streetchampionproject.api.scs.response

import com.google.gson.annotations.SerializedName

data class NotificationForSend(
    @SerializedName("notificationId")
    var notificationId: Int,

    @SerializedName("senderId")
    var senderId: Int,

    @SerializedName("recipientId")
    var recipientId: Int,

    @SerializedName("notificationType")
    var notificationType: Int,

    @SerializedName("notificationStatus")
    var notificationStatus: Int
    )
