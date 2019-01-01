package com.stylab.test.data

import java.io.IOException

suspend fun <T : Any> saferApiCall(call: suspend () -> Result<T>, errorMessage: String): Result<T> {
    return try {
        call()
    } catch (e: Exception) {
        // Exception converting this to an IOException
        Result.Error(IOException(errorMessage, e))
    }
}
