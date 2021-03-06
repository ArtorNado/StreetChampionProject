package com.example.streetchampionproject.api.scs.models

import com.google.gson.annotations.SerializedName

data class Notification(

    @SerializedName("notificationId")
    var notificationId: Int,

    @SerializedName("senderId")
    var senderId: Int,

    @SerializedName("recipientId")
    var recipientId: Int,

    @SerializedName("notificationType")
    var notificationType: Int,

    @SerializedName("notificationStatus")
    var notificationStatus: Int,

    @SerializedName("senderData")
    var userMainData: UserMainData

)
