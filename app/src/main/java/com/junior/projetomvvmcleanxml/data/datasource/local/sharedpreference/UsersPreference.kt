package com.junior.projetomvvmcleanxml.data.datasource.local.sharedpreference

import android.content.Context
import androidx.core.content.edit
import com.junior.projetomvvmcleanxml.domain.repository.UserSessionDataSource

class UsersPreference( context: Context): UserSessionDataSource {
    private val prefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)


    override fun saveUserId(userId: String, name: String) {

        prefs.edit {
            putString("user_id", userId)
            putString("name", name)
        }

    }

    override fun getUserId(): String?{
        val userId =  prefs.getString("user_id", null)


        return userId

    }

    override fun getUserName(): String? {
        val name = prefs.getString("name", null)

        return name

    }

    override fun clear(){
        prefs.edit { clear() }
    }


}