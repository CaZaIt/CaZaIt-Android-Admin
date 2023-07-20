package org.cazait.data.caller

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.bmsk.domain.DomainResult
import org.bmsk.domain.exception.DomainError
import org.bmsk.domain.exception.ErrorType
import org.cazait.network.dto.response.CazaitResponse
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ApiCallerImpl @Inject constructor(
    private val ioDispatcher: CoroutineContext
) : ApiCaller {
    override suspend fun safeApiCall(call: suspend () -> Flow<Result<CazaitResponse<*>>>): Flow<DomainResult<String>> {
        return flow<DomainResult<String>> {
            call().first()
                .onFailure { exception ->
                    when (exception) {
                        is IOException -> emit(DomainResult.Error(DomainError.NetworkError(null)))
                        else -> emit(DomainResult.Error(DomainError.UnKnownError(null)))
                    }
                }
                .onSuccess {
                    if (it.data == null) {
                        emit(DomainResult.Error(DomainError.InvalidInputError(it.message)))
                    } else {
                        emit(DomainResult.Success(it.message))
                    }
                }

        }.flowOn(ioDispatcher)
    }

    override suspend fun <T, R> safeApiCallWithData(
        call: suspend () -> Flow<Result<CazaitResponse<T>>>,
        asDomain: (T) -> R,
        onNullDataError: ErrorType?,
        needServerDescription: Boolean
    ): Flow<DomainResult<R>> {
        return flow<DomainResult<R>> {
            call().first()
                .onFailure { exception ->
                    when (exception) {
                        is IOException -> emit(DomainResult.Error(DomainError.NetworkError(null)))
                        else -> emit(DomainResult.Error(DomainError.UnKnownError(null)))
                    }
                }
                .onSuccess {
                    val data = it.data
                    if (data == null) {
                        if (onNullDataError == null) {
                            emit(DomainResult.Error(DomainError.InvalidInputError(it.message)))
                        } else {
                            emit(
                                DomainResult.Error(
                                    createDomainError(
                                        errorType = onNullDataError,
                                        serverDescription = if (needServerDescription) it.message else null
                                    )
                                )
                            )
                        }
                    } else {
                        emit(DomainResult.Success(asDomain(data)))
                    }
                }

        }.flowOn(ioDispatcher)
    }

    private fun createDomainError(errorType: ErrorType, serverDescription: String? = null) =
        when (errorType) {
            ErrorType.NETWORK_ERROR -> DomainError.NetworkError(serverDescription)
            ErrorType.INVALID_INPUT_ERROR -> DomainError.InvalidInputError(serverDescription)
            ErrorType.NOT_FOUND -> DomainError.NotFound(serverDescription)
            ErrorType.UNKNOWN_ERROR -> DomainError.UnKnownError(serverDescription)
            ErrorType.AUTHORIZATION_ERROR -> DomainError.AuthorizationError(serverDescription)
        }
}