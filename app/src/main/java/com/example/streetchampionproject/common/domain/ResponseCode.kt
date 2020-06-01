package com.example.streetchampionproject.common.domain

import com.example.streetchampionproject.R

enum class ResponseCode(val stringResource: Int) {
    INVALID_LOG_PAS(R.string.invalid_log_pas),
    INTERNET_ERROR(R.string.internet_connection),
    CITY_NOT_FOUND(R.string.city_not_found),
    SERVER_ERROR(R.string.server_error),
    JOIN_TEAM_ERROR(R.string.error_joining_in_team),
    MATCH_TYPE_ERROR(R.string.match_type_error),
    USER_NOT_ADMIN(R.string.create_not_enough_rights),
    USER_ALREADY_HAVE_TEAM(R.string.already_have_team),
    LOGIN_ALREADY_EXIST(R.string.already_have_team),
    MAX_PLAYERS(R.string.max_players)
}
