package org.cazait.network.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.internal.http.HTTP_UNAUTHORIZED
import org.bmsk.domain.exception.EmptyDataException
import org.bmsk.domain.exception.EmptyResponseBodyException
import org.bmsk.domain.exception.UnauthorizedException
import org.cazait.network.dto.response.CazaitResponse
import retrofit2.Response

suspend fun <T> processCall(call: suspend () -> Response<CazaitResponse<T>>): Flow<Result<T>> {
    return flow {
        kotlin.runCatching {
            val response = call()

            if (response.isSuccessful) {
                response.body()?.let {
                    if (it.data == null) emit(Result.failure(EmptyDataException()))
                    else emit(Result.success(it.data))
                } ?: emit(Result.failure(EmptyResponseBodyException()))
            } else {
                emit(Result.failure(handleFailure(response.code())))
            }
        }
    }
}

fun handleFailure(responseCode: Int) = when (responseCode) {
    HTTP_UNAUTHORIZED -> UnauthorizedException()
    else -> Exception("Unexpected error occurred")
}
