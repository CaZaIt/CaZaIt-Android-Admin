package org.bmsk.domain.usecase

import org.bmsk.domain.exception.DomainError
import javax.inject.Inject

class ErrorUseCase @Inject constructor() {
    fun getServerDescriptionOrNull(error: DomainError): String? =
        when (error) {
            is DomainError.NetworkError -> {
                error.serverDescription
            }
            is DomainError.AuthorizationError -> {
                error.serverDescription
            }
            is DomainError.UnKnownError -> {
                error.serverDescription
            }
            is DomainError.InvalidInputError -> {
                error.serverDescription
            }
            is DomainError.NotFound -> {
                error.serverDescription
            }
        }
}