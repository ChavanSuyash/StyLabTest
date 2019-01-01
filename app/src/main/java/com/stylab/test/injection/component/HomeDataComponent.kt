package com.stylab.test.injection.component

import com.stylab.test.features.home.HomeActivity
import com.stylab.test.injection.module.*
import com.stylab.test.injection.scope.ActivityScope
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        LoginRepositoryModule::class,
        HomeDataModule::class,
        HomeViewModelModule::class
    ], dependencies = [
        CoreDataComponent::class
    ]
)
@ActivityScope
interface HomeDataComponent {
    @Component.Builder interface Builder {
        fun build() : HomeDataComponent

        fun coreDataComponent(coreDataComponent: CoreDataComponent) : Builder
        fun loginRepositoryModule(loginRepositoryModule: LoginRepositoryModule) : Builder
        fun sharedPreferencesModule(sharedPreferencesModule: SharedPreferencesModule) : Builder
    }

    fun inject(homeActivity: HomeActivity)

}
