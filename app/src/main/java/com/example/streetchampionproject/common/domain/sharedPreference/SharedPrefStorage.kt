package com.example.streetchampionproject.common.domain.sharedPreference

import android.content.Context
import android.util.Log

class SharedPrefStorage(
    private val context: Context
) : LocalStorage {

    override fun writeMessage(name: String, message: String?) {
        context.getSharedPreferences("sharedPreference", Context.MODE_PRIVATE)
            .edit().putString(name, message).apply()
    }

    override fun readMessage(name: String): String? {
        Log.e("READ","READ")
        return context.getSharedPreferences("sharedPreference", Context.MODE_PRIVATE).getString(name, "")
    }
}
