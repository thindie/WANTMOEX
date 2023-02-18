package com.example.thindie.wantmoex.domain

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

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

fun <T, R> Results.Success<T>.transform(mapper: (T) -> R): Results.Success<R> {
    return Results.Success(mapper(this.data))
}

fun <T, R> Results<T>.transformResult(mapper: (T) -> R): Results<R> {
    return this.result { mapper(it) }
}


fun <T, R> Results<T>.resultOnFlow(mapper: (T) -> R): Flow<Results<R>> {
    val r = this
    return flow {
        when (r) {
            is Results.Success<*> -> {
                emit(r.transform { mapper } as Results<R>)
            }
            is Results.Error -> {
                emit(Results.Error(Exception("on resultOnFlow")))
            }
        }
    }
}

fun <T, R> Results<T>.result(mapper: (T) -> R): Results<R> {

    return when (this) {
        is Results.Success<T> -> {
            this.transform {  mapper(it) }
        }
        is Results.Error -> {
            Results.Error(Exception("on result"))
        }
    }
}


fun <T> Flow<T>.handleErrors(): Flow<T> =
    catch { e -> Log.d("SERVICE_TAG", "$e") }


fun <T, R> Results.Success<T>.unpackResult(mapper: (T) -> R): R {
    return mapper(this.data)
}

  fun <T, R> Flow<Results<T>>.mutateFlow(mapper: (T) -> R): Flow<Results<R>> {
    val f = this
    return flow {
        f.collect {
          val t =   it.result { r -> mapper(r) }
            emit(t)
        }
    }
}