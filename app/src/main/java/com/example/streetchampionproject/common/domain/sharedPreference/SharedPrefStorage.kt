package com.example.streetchampionproject.common.domain.sharedPreference

import android.content.Context
import javax.inject.Singleton

@Singleton
class SharedPrefStorage(
    private val context: Context
) : LocalStorage {

    override fun writeMessage(name: String, message: String?) {
        context.getSharedPreferences("sharedPreference", Context.MODE_PRIVATE)
            .edit().putString(name, message).apply()
    }

    override fun readMessage(name: String): String? =
        context.getSharedPreferences("sharedPreference", Context.MODE_PRIVATE)
            .getString(name, "")
    
}
