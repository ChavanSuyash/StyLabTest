package com.stylab.test.base

import com.stylab.test.data.login.LoginLocalDataSource
import com.stylab.test.injection.component.DaggerLoginRepositoryComponent
import com.stylab.test.injection.module.LoginRepositoryModule
import com.stylab.test.injection.module.SharedPreferencesModule

fun BaseActivityNavigator.inject() {

    DaggerLoginRepositoryComponent.builder()
        .loginModule(LoginRepositoryModule())
        .sharedPreferencesModule(SharedPreferencesModule(this, LoginLocalDataSource.LOGIN_PREF))
        .build()
        .inject(this)

}