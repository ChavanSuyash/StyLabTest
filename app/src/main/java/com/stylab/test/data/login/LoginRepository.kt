package com.stylab.test.data.login

import com.stylab.test.data.login.model.LoggedInUser

class LoginRepository(private val loginLocalDataSource: LoginLocalDataSource) {

    //local cache of user object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        user = loginLocalDataSource.user
    }

    fun setLoggedInUser(loggedInUser: LoggedInUser) {
        user = loggedInUser
        loginLocalDataSource.user = loggedInUser
    }

    fun logout() {
        user = null
        loginLocalDataSource.logout()
    }

    companion object {
        @Volatile
        private var INSTANCE: LoginRepository? = null

        fun getInstance(loginLocalDataSource: LoginLocalDataSource) : LoginRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: LoginRepository(loginLocalDataSource).also { INSTANCE = it }
            }
        }
    }

}