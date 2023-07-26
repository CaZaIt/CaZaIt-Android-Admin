package org.cazait.data.caller

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import org.bmsk.domain.DomainResult
import org.bmsk.domain.exception.DomainError
import org.bmsk.domain.exception.EmptyDataException
import org.bmsk.domain.exception.EmptyResponseBodyException
import org.bmsk.domain.exception.ErrorType
import org.bmsk.domain.exception.UnauthorizedException
import org.cazait.network.dto.response.CazaitResponse
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ApiCallerImpl @Inject constructor(
    private val ioDispatcher: CoroutineContext
) : ApiCaller {
    override suspend fun safeApiCall(call: suspend () -> Flow<Result<*>>): Flow<DomainResult<String>> {
        return call().transform<Result<*>, DomainResult<String>> { it ->
            it.onFailure { exception ->
                emit(DomainResult.Error(exception))
            }.onSuccess {
                emit(DomainResult.Success("success"))
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun <T, R> safeApiCallWithData(
        call: suspend () -> Flow<Result<T>>,
        asDomain: (T) -> R,
    ): Flow<DomainResult<R>> {
        return call().transform<Result<T>, DomainResult<R>> { it ->
            it.onFailure { exception ->
                emit(DomainResult.Error(exception))
            }.onSuccess {
                emit(DomainResult.Success(asDomain(it)))
            }
        }.flowOn(ioDispatcher)
    }
}