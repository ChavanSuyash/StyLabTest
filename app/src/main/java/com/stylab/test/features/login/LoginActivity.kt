package com.stylab.test.features.login

import android.content.Intent
import android.os.Bundle
import android.transition.TransitionManager
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.stylab.test.R
import com.stylab.test.databinding.ActivityLoginBinding
import com.stylab.test.features.home.HomeActivity
import com.stylab.test.util.ActivityNavigation
import com.stylab.test.util.startActivity
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), ActivityNavigation {

    private lateinit var binding : ActivityLoginBinding

    @Inject
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject()

        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        binding.loginViewModel = viewModel

        subscribeUi()
    }

    private fun subscribeUi() {
        viewModel.startActivityForResultEvent.setEventReceiver(this, this)

        viewModel.uiState.observe( this, Observer {
                val uiModel = it

                if (uiModel.showError != null && !uiModel.showError.consumed) {
                    uiModel.showError.consume()?.let { showLoginFailed(it) }
                }

                if (uiModel.showSuccess != null && !uiModel.showSuccess.consumed) {
                        uiModel.showSuccess.consume()?.let { showLoginSuccess(it) }
                }
            }
        )
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        viewModel.onResultFromActivity(requestCode,resultCode,data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun showLoginSuccess(@StringRes successString : Int){
        startActivity<HomeActivity>()
        finish()
    }

    private fun showLoginFailed(@StringRes errorString : Int) {
        Snackbar.make(binding.container, errorString, Snackbar.LENGTH_SHORT).show()
        beginDelayedTransition()
    }

    private fun beginDelayedTransition() {
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
            TransitionManager.beginDelayedTransition(binding.container)
    }

}