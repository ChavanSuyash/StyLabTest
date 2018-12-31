package com.stylab.test.features.login

import com.stylab.test.data.login.LoginLocalDataSource
import com.stylab.test.injection.component.DaggerLoginComponent
import com.stylab.test.injection.module.GoogleSignInClientModule
import com.stylab.test.injection.module.LoginRepositoryModule
import com.stylab.test.injection.module.SharedPreferencesModule

fun LoginActivity.inject() {
    DaggerLoginComponent.builder()
        .googleSignInClientModule(GoogleSignInClientModule(this))
        .loginRepositoryModule(LoginRepositoryModule())
        .sharedPreferencesModule(SharedPreferencesModule(this,LoginLocalDataSource.LOGIN_PREF))
        .build()
        .inject(this)
}