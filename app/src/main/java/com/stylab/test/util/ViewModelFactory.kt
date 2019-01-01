package com.stylab.test.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stylab.test.injection.scope.ActivityScope
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@ActivityScope
class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModels[modelClass]?.get() as T

}