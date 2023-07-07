package org.bmsk.domain

sealed class Result<T> {
    data class Loading<T>(val any: Any?) : Result<T>()
    data class Success<T>(val data: T) : Result<T>()
    data class Fail<T>(val message: String, val data: T? = null): Result<T>()
    data class Error<T>(val error: ErrorType, val data: T? = null) : Result<T>()
}

enum class ErrorType {
    NETWORK_CONNECTION_ERROR,
    NO_DATA_ERROR,

    UNEXPECTED_ERROR,
}