package com.stylab.test.util

import android.content.Intent

interface ActivityNavigation {

    fun startActivityForResult(intent: Intent?, requestCode: Int)

}