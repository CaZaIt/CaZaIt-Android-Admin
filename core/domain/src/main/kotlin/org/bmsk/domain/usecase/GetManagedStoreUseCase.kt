package org.bmsk.domain.usecase

import org.bmsk.domain.repository.StoreRepository
import org.bmsk.domain.repository.UserRepository
import javax.inject.Inject

class GetManagedStoreUseCase @Inject constructor(
    private val storeRepository: StoreRepository,
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke() = userRepository.getCurrentUser().map {
        storeRepository.getManagedCafeList(it.uuid)
    }
}