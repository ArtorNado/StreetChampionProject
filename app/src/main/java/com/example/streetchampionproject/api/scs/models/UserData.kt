package com.example.streetchampionproject.api.scs.models

data class UserData(
    var userId: Int,
    var userFirstName: String,
    var userSecondName: String,
    var userGender: String,
    var userCity: String,
    var team: String
)
