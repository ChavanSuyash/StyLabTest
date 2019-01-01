package com.stylab.test

import android.app.Application
import android.content.Context
import com.stylab.test.injection.component.CoreDataComponent
import com.stylab.test.injection.component.DaggerCoreDataComponent
import com.stylab.test.injection.module.CoreDataModule

class StyLabApplication : Application() {

    private val coreDataComponent: CoreDataComponent by lazy {
        DaggerCoreDataComponent
            .builder()
            .coreDataModule(CoreDataModule())
            .build()
    }

    companion object {
        @JvmStatic
        fun coreDataComponent(context: Context) =
            (context.applicationContext as StyLabApplication).coreDataComponent
    }
}