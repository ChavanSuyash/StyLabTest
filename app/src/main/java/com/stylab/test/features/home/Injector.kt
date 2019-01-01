package com.stylab.test.features.home

import com.stylab.test.StyLabApplication
import com.stylab.test.data.login.LoginLocalDataSource
import com.stylab.test.injection.component.DaggerHomeDataComponent
import com.stylab.test.injection.module.LoginRepositoryModule
import com.stylab.test.injection.module.SharedPreferencesModule

fun HomeActivity.inject() {
    DaggerHomeDataComponent.builder()
        .coreDataComponent(StyLabApplication.coreDataComponent(this))
        .loginRepositoryModule(LoginRepositoryModule())
        .sharedPreferencesModule(SharedPreferencesModule(this, LoginLocalDataSource.LOGIN_PREF))
        .build()
        .inject(this)
}
