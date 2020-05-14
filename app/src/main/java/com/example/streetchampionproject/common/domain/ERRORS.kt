package com.example.streetchampionproject.common.domain

interface ERRORS {

    interface NAME {
        companion object {
            const val HTTP_500 = "HTTP 500 "
            const val HTTP_403 = "HTTP 403 "
        }
    }

    interface MESSAGE{
        companion object{
            const val WRONG_LOG_PAS = "Неверный логин или пароль"
            const val ALREADY_IN_TEAM = "Вы уже состоите в команде"
            const val NETWORK_EXCEPTION = "Ошибка интернет соединения"
        }
    }
}
