package com.stylab.test.util

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

inline fun <reified T : AppCompatActivity> AppCompatActivity.startActivity() {
    startActivity(Intent(this, T::class.java))
}