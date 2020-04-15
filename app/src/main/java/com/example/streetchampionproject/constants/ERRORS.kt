package com.example.streetchampionproject.constants

interface ERRORS {

    interface NAME {
        companion object {
            const val HTTP_500 = "HTTP 500 "
        }
    }

    interface MESSAGE{
        companion object{
            const val WRONG_LOG_PAS = "Неверный логин или пароль"
        }
    }
}
