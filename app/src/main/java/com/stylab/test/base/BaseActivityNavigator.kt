package com.stylab.test.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stylab.test.data.login.LoginRepository
import com.stylab.test.features.home.HomeActivity
import com.stylab.test.features.login.LoginActivity
import javax.inject.Inject

class BaseActivityNavigator : AppCompatActivity() {

    @Inject
    lateinit var loginRepository : LoginRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject()

        val intent = if(loginRepository.isLoggedIn) {
            Intent(this, HomeActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }

        startActivity(intent)

        finish()
    }



}