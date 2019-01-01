package com.stylab.test.injection.component

import com.stylab.test.base.BaseActivityNavigator
import com.stylab.test.injection.module.LoginRepositoryModule
import com.stylab.test.injection.module.SharedPreferencesModule
import dagger.Component

@Component(
    modules = [
        LoginRepositoryModule::class
    ]
)
interface LoginRepositoryComponent{
   @Component.Builder interface Builder {
       fun build() : LoginRepositoryComponent

       fun loginModule(loginRepositoryModule: LoginRepositoryModule) : Builder
       fun sharedPreferencesModule(sharedPreferencesModule: SharedPreferencesModule) : Builder
   }

    fun inject(navigator: BaseActivityNavigator)
}