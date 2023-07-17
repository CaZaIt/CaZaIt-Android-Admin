package org.bmsk.domain.exception

import org.bmsk.domain.R

enum class ErrorType {
    NETWORK_ERROR,
    NOT_FOUND,
    AUTHORIZATION_ERROR,
    INVALID_INPUT_ERROR,
    UNKNOWN_ERROR,
}

sealed class DomainError(val errorType: ErrorType, val messageResId: Int) {
    data class NetworkError(val serverDescription: String?) :
        DomainError(ErrorType.NETWORK_ERROR, R.string.network_error_occurred)

    data class NotFound(val serverDescription: String?) :
        DomainError(ErrorType.NOT_FOUND, R.string.data_not_found)

    data class AuthorizationError(val serverDescription: String?) :
        DomainError(ErrorType.AUTHORIZATION_ERROR, R.string.authorization_error_occurred)

    data class InvalidInputError(val serverDescription: String?) :
        DomainError(ErrorType.INVALID_INPUT_ERROR, R.string.invalid_input_error)

    data class UnKnownError(val serverDescription: String?) :
        DomainError(ErrorType.UNKNOWN_ERROR, R.string.unknown_error_occured)
}
