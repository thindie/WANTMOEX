package com.example.thindie.wantmoex.domain

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

sealed class Results<out R> {

    data class Success<out T>(val data: T) : Results<T>()
    data class Error(val exception: Exception) : Results<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}

fun <T, R> Results.Success<T>.transformSuccessHandle(mapper: (T) -> R): Results.Success<R> {
    return Results.Success(mapper(this.data))
}

fun <T : Any?> T.encapsulateResult(): Results<T> {
    val t: T = this
    if (t as Any? == null) {
        return Results.Error(
            NullPointerException("Bad Results - null obj on encapsulation")
        )
    }
    if (t is Collection<*>) {
        if ((t as Collection<*>).isEmpty()) {
            return Results.Error(
                NullPointerException("Bad Results - empty Iterable<{$t}> here. data lost / or not received")
            )
        }
    }


    return Results.Success(t)
}


fun <T, R> Results<T>.result(mapper: (T) -> R): Results<R> {
    return when (this) {
        is Results.Success<T> -> {
            this.transformSuccessHandle { mapper(it) }
        }
        is Results.Error -> {
            Results.Error(Exception(this.toString()))
        }
    }
}

fun <T> Flow<T>.handleErrors(): Flow<T> =
    catch { e -> Log.d("SERVICE_TAG", "$e") }


suspend fun <T, R> Results<T>.reDirectSource(
    redirect: suspend () -> Results<R>,
    mapper: (T) -> R,
): Results<R> {
    var r: Results<R>? = null
    this.unpackResult {
        when (it) {
            is Results.Success<*> -> {
                r = Results.Success(mapper(it))
            }
            else -> {}
        }
    }
    return r ?: redirect()
}


fun <T, R : Any> Results<T>.unpackResult(mapper: (T) -> R): R? {
    when (this) {
        is Results.Success -> {
            if (this.data == null) {
                return null
            }
            if (this.data is Collection<*>) {
                return if ((this.data as Collection<*>).isEmpty()) {
                    null
                } else mapper(this.data)
            }
            if (this.data !is Collection<*>) {
                return mapper(this.data)
            }
            return mapper(this.data)
        }
        is Results.Error -> {
            return null
        }
    }
 }

fun <T> Results<T>.unpackResult(): T? {
    when (this) {
        is Results.Success -> {
            if (this.data == null) {
                return null
            }
            if (this.data is Collection<*>) {
                return if ((this.data as Collection<*>).isEmpty()) {
                    null
                } else this.data
            }
            if (this.data !is Collection<*>) {
                return this.data
            }
            return this.data
        }
        is Results.Error -> {
            return null
        }
    }

}

