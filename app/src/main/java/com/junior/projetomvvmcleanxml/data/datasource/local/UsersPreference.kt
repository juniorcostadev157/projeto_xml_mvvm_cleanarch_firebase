package com.junior.projetomvvmcleanxml.data.datasource.local

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import com.junior.projetomvvmcleanxml.domain.repository.UserSessionDataSource

class UsersPreference( context: Context): UserSessionDataSource {
    private val prefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
    private val TAG = "UsersPreference"

    override fun saveUserId(userId: String) {
        Log.d(TAG, "🔹 Salvando userId: $userId")
        prefs.edit { putString("user_id", userId) }
    }

    override fun getUserId(): String?{
        val userId =  prefs.getString("user_id", null)
        Log.d(TAG, "🔹 Salvando userId: $userId")
        return userId

    }

    override fun clear(){
        prefs.edit { clear() }
    }


}