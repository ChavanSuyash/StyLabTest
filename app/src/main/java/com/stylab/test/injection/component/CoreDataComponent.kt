package com.stylab.test.injection.component

import com.stylab.test.injection.module.CoreDataModule
import dagger.Component
import javax.inject.Singleton

@Component( modules = [
        CoreDataModule::class
    ]
)
@Singleton
interface CoreDataComponent {

    @Component.Builder interface Builder {
        fun build(): CoreDataComponent

        fun coreDataModule(coreDataModule: CoreDataModule) : Builder
    }
}
