package org.bmsk.domain.usecase

import org.bmsk.domain.model.CongestionStatus
import org.bmsk.domain.repository.CacheRepository
import org.bmsk.domain.repository.StoreRepository
import org.bmsk.domain.repository.UserRepository
import java.util.UUID
import javax.inject.Inject

class StoreStateUpdateUseCase @Inject constructor(
    private val repository: StoreRepository,
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(congestionStatus: CongestionStatus) {
        val selectedManagedCafe = CacheRepository.selectedManagedCafe
        val userId = userRepository.getCurrentUser().getOrElse {
            userRepository.deleteUserInformation()
        }.uuid
        repository.updateCafeCongestionStatus(
            userId = userId,
            cafeId = selectedManagedCafe.value.cafeId,
            congestionStatus = congestionStatus
        )
    }
}