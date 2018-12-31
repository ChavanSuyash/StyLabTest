package com.stylab.test.injection.module

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.Module
import dagger.Provides

@Module
class GoogleSignInClientModule(val context: Context) {

    @Provides
    fun providesGoogleSignInClient() : GoogleSignInClient {
        return GoogleSignIn.getClient(context
            , GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build())
    }
}