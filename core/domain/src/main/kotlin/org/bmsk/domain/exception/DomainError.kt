package org.bmsk.domain.exception

import org.bmsk.domain.R

enum class ErrorType{
    NETWORK_ERROR,
    NOT_FOUND,
    AUTHORIZATION_ERROR,
    INVALID_INPUT_ERROR,
    UNKNOWN_ERROR,
}

enum class DomainError(val errorType: ErrorType, val messageResId: Int, var serverDescription: String? = null) {
    NetworkError(ErrorType.NETWORK_ERROR, R.string.network_error_occurred),
    NotFound(ErrorType.NOT_FOUND, R.string.data_not_found),
    AuthorizationError(ErrorType.AUTHORIZATION_ERROR, R.string.authorization_error_occurred),
    InvalidInputError(ErrorType.INVALID_INPUT_ERROR, R.string.invalid_input_error),
    UnKnownError(ErrorType.UNKNOWN_ERROR, R.string.unknown_error_occured)
}

fun handleServerError(errorType: ErrorType, errorMessage: String?): DomainError {
    val domainError = when (errorType) {
        ErrorType.NETWORK_ERROR -> DomainError.NetworkError
        ErrorType.NOT_FOUND -> DomainError.NotFound
        ErrorType.AUTHORIZATION_ERROR -> DomainError.AuthorizationError
        ErrorType.INVALID_INPUT_ERROR -> DomainError.InvalidInputError
        ErrorType.UNKNOWN_ERROR -> DomainError.UnKnownError
    }
    domainError.serverDescription = errorMessage

    return domainError
}