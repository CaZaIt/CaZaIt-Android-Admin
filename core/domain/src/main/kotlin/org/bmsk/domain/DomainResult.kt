package org.bmsk.domain

sealed class DomainResult<T> {
    data class Success<T>(val data: T) : DomainResult<T>()
    data class Error<T>(val exception: Throwable) : DomainResult<T>()
}