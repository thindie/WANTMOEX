package com.example.core.network.common

import android.util.Log
import retrofit2.Response

inline fun <C, R : Response<C>> response(
    foo: () -> R,
): C? {
    return try {
        if (foo.invoke().isSuccessful) {
            foo().body()
        } else {
            null
        }
    } catch (e: Exception) { // todo( java.net.UnknownHostException?
        Log.d("SERVICE_TAG", "$e")
        null
    }
}
