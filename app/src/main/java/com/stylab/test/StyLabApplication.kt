package com.stylab.test

import android.content.Context
import com.stylab.test.injection.component.CoreDataComponent
import com.stylab.test.injection.component.DaggerCoreDataComponent
import com.stylab.test.injection.module.CoreDataModule
import com.stylab.test.util.OkHttpLibGlideModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class StyLabApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerCoreDataComponent.builder()
            .coreDataModule(CoreDataModule())
            .build()
    }

    companion object {
        @JvmStatic
        fun coreDataComponent(context: Context) =
            (context.applicationContext as StyLabApplication).getCoreData()
    }

    fun inject(appGlideModule: OkHttpLibGlideModule) {
        (applicationInjector() as CoreDataComponent).glideComponentBuilder().build().inject(appGlideModule)
    }

    fun getCoreData() : CoreDataComponent{
        return applicationInjector() as CoreDataComponent
    }

}