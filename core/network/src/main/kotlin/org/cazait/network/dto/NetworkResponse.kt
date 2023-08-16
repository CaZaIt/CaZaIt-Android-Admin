package org.cazait.network.dto

sealed class NetworkResponse<out T> {
    data class Success<out T>(val data: T): NetworkResponse<T>()
    data class Error(val errorData: ErrorData): NetworkResponse<Nothing>()
}
