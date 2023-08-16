package org.bmsk.domain.exception

enum class ErrorType {
    NETWORK_ERROR,
    NOT_FOUND,
    AUTHORIZATION_ERROR,
    INVALID_INPUT_ERROR,
    UNKNOWN_ERROR,
}

sealed class DomainError(val serverDescription: String?, val errorType: ErrorType) {
    class NetworkError(serverDescription: String?) :
        DomainError(serverDescription, ErrorType.NETWORK_ERROR)

    class NotFound(serverDescription: String?) :
        DomainError(serverDescription, ErrorType.NOT_FOUND)

    class AuthorizationError(serverDescription: String?) :
        DomainError(serverDescription, ErrorType.AUTHORIZATION_ERROR)

    class InvalidInputError(serverDescription: String?) :
        DomainError(serverDescription, ErrorType.INVALID_INPUT_ERROR)

    class UnKnownError(serverDescription: String?) :
        DomainError(serverDescription, ErrorType.UNKNOWN_ERROR)
}
