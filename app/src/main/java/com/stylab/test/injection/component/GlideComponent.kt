package com.stylab.test.injection.component

import com.stylab.test.injection.module.GlideDaggerModule
import com.stylab.test.util.OkHttpLibGlideModule
import dagger.Subcomponent

@Subcomponent(modules = [
    GlideDaggerModule::class
])
interface GlideComponent {

    fun inject(okHttpLibGlideModule: OkHttpLibGlideModule)

    @Subcomponent.Builder
    interface Builder {

        fun glideModule(glideModule: GlideDaggerModule): Builder

        fun build(): GlideComponent
    }
}