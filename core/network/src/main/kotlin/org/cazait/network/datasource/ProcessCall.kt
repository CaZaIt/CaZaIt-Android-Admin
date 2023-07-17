package org.cazait.network.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.cazait.network.dto.response.CazaitResponse
import retrofit2.Response

suspend fun <T> processCall(call: suspend () -> Response<CazaitResponse<T>>): Flow<Result<CazaitResponse<T>>> {
    return flow {
        try {
            val response = call()

            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(Result.success(body))
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Unknown Error"
                emit(Result.failure(Exception(errorMessage)))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}