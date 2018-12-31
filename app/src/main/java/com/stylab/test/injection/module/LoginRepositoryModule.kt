package com.stylab.test.injection.module

import android.content.SharedPreferences
import com.stylab.test.data.login.LoginLocalDataSource
import com.stylab.test.data.login.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module(includes = [SharedPreferencesModule::class])
class LoginRepositoryModule {

    @Reusable
    @Provides
    fun providesLoginRepository(localDataSource: LoginLocalDataSource) : LoginRepository {
        return LoginRepository.getInstance(localDataSource)
    }

    @Reusable
    @Provides
    fun provideLoginLocalDataSource(preferences: SharedPreferences): LoginLocalDataSource =
        LoginLocalDataSource(preferences)

}