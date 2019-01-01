package com.stylab.test.injection.component

import com.stylab.test.features.login.LoginActivity
import com.stylab.test.injection.module.*
import com.stylab.test.injection.scope.ActivityScope
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        LoginRepositoryModule::class,
        GoogleSignInClientModule::class,
        LoginViewModelModule::class
    ]
)
@ActivityScope
interface LoginComponent {
   @Component.Builder interface Builder {

       fun build() : LoginComponent

       fun loginRepositoryModule(loginRepositoryModule: LoginRepositoryModule) : LoginComponent.Builder
       fun sharedPreferencesModule(sharedPreferencesModule: SharedPreferencesModule) : LoginComponent.Builder

       fun googleSignInClientModule(googleSignInClientModule: GoogleSignInClientModule) : LoginComponent.Builder
   }

    fun inject(loginActivity: LoginActivity)
}