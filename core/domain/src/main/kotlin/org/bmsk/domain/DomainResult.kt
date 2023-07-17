package org.bmsk.domain

import org.bmsk.domain.exception.DomainError

sealed class DomainResult<T> {
    data class Loading<T>(val any: Any?) : DomainResult<T>()
    data class Success<T>(val data: T) : DomainResult<T>()
    data class Error<T>(val error: DomainError) : DomainResult<T>()
}