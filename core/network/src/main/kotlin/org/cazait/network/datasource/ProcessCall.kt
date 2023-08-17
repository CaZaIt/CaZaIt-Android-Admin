package org.cazait.network.datasource

import android.accounts.NetworkErrorException
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.bmsk.domain.exception.EmptyDataException
import org.cazait.network.dto.response.CazaitResponse
import retrofit2.Response

fun <T> processCall(call: suspend () -> Response<CazaitResponse<T>>): Flow<Result<T>> {
    return flow {
        val response = call()
        if (response.isSuccessful) {
            response.body()?.let { cazaitResponse ->
                emit(
                    if (cazaitResponse.data != null) Result.success(cazaitResponse.data)
                    else Result.failure(EmptyDataException())
                )
            }
        } else {
            emit(Result.failure(NetworkErrorException()))
        }
    }
}

fun <T> normalProcessCall(call: suspend () -> Response<T>): Flow<Result<T>> {
    return flow {
        val response = call()
        Log.d("ProcessCall", response.toString())
        if (response.isSuccessful) {
            response.body()?.let { res ->
                emit(Result.success(res))
            }
        } else {
            emit(Result.failure(NetworkErrorException()))
        }
    }
}
