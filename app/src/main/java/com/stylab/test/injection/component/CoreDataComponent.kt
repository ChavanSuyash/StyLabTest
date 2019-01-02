package com.stylab.test.injection.component

import com.stylab.test.StyLabApplication
import com.stylab.test.injection.module.CoreDataModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component( modules = [
        CoreDataModule::class
    ]
)
interface CoreDataComponent : AndroidInjector<StyLabApplication>{

    fun glideComponentBuilder(): GlideComponent.Builder

    @Component.Builder interface Builder {
        fun build(): CoreDataComponent

        fun coreDataModule(coreDataModule: CoreDataModule) : Builder
    }
}
