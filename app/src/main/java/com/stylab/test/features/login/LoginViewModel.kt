package com.stylab.test.features.login

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.stylab.test.R
import com.stylab.test.data.login.LoginRepository
import com.stylab.test.data.login.model.LoggedInUser
import com.stylab.test.util.ActivityNavigation
import com.stylab.test.util.Event
import com.stylab.test.util.LiveMessageEvent
import javax.inject.Inject
import kotlin.random.Random

const val GOOGLE_SIGN_IN : Int = 9001

class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val googleSignInClient: GoogleSignInClient
): ViewModel() {

    val startActivityForResultEvent = LiveMessageEvent<ActivityNavigation>()

    private val _uiState = MutableLiveData<LoginUiModel>()
    val uiState: LiveData<LoginUiModel>
        get() = _uiState

    fun googleSignUp() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResultEvent.sendEvent { startActivityForResult(signInIntent, GOOGLE_SIGN_IN) }
    }

    fun onResultFromActivity(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            GOOGLE_SIGN_IN -> {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                googleSignInComplete(task)

            }
            else ->{
                emitUiState(
                    showError = Event(R.string.login_failed)
                )
            }
        }
    }

    private fun googleSignInComplete(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            account?.apply {
                loginRepository.setLoggedInUser(
                    LoggedInUser(
                        Random.nextLong(),
                        this.displayName!!
                    )
                )
                emitUiState(
                    showSuccess = Event(R.string.login_successful)
                )
            }
        }catch (e: ApiException) {
            emitUiState(
                showError = Event(R.string.login_failed)
            )
        }
    }

    private fun emitUiState(
        showSuccess: Event<Int>? = null,
        showError: Event<Int>? = null
    ) {
        val uiModel = LoginUiModel(showSuccess,showError)
        _uiState.value = uiModel
    }

}

data class LoginUiModel(
    val showSuccess : Event<Int>?,
    val showError : Event<Int>?
)