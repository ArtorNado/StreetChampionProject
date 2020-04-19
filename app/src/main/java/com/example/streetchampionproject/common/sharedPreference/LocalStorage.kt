package com.example.streetchampionproject.common.sharedPreference

interface LocalStorage {

    fun writeMessage(name: String, message: String?)
    fun readMessage(name: String): String?

}
