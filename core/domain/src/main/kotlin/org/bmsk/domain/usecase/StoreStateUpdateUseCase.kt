package org.bmsk.domain.usecase

import org.bmsk.domain.model.CongestionStatus
import org.bmsk.domain.repository.CacheRepository
import org.bmsk.domain.repository.StoreRepository
import javax.inject.Inject

class StoreStateUpdateUseCase @Inject constructor(
    private val repository: StoreRepository,
) {
    operator fun invoke(congestionStatus: CongestionStatus) {
        val selectedManagedCafe = CacheRepository.selectedManagedCafe

        repository.updateCafeCongestionStatus(
            cafeId = selectedManagedCafe.value.cafeId,
            congestionStatus = congestionStatus
        )
    }
}