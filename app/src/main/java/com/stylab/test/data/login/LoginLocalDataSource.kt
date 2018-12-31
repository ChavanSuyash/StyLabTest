package com.stylab.test.data.login

import android.content.SharedPreferences
import androidx.core.content.edit
import com.stylab.test.data.login.model.LoggedInUser

class LoginLocalDataSource(private val prefs : SharedPreferences) {

    var user : LoggedInUser?
        get() {
            val userId = prefs.getLong(KEY_USER_ID, 0L)
            val fullName = prefs.getString(KEY_USER_FULL_NAME, null)
            if (userId == 0L || fullName == null) {
                return null
            }
            return LoggedInUser(
                id = userId,
                fullName = fullName
            )
        }
        set(value) {
            if(value != null) {
                prefs.edit {
                    putLong(KEY_USER_ID, value.id)
                    putString(KEY_USER_FULL_NAME, value.fullName)
                }
            }
        }


    fun logout() {
        prefs.edit {
            putLong(KEY_USER_ID, 0L)
            putString(KEY_USER_FULL_NAME, null)
        }
    }


    companion object {
        const val LOGIN_PREF = "LOGIN_PREF"
        private const val KEY_USER_ID = "KEY_USER_ID"
        private const val KEY_USER_FULL_NAME = "KEY_USER_FULL_NAME"
    }
}