package org.bmsk.domain.usecase

import org.bmsk.domain.repository.StoreRepository
import javax.inject.Inject

class StoreUseCase @Inject constructor(
    private val storeUseCase: StoreRepository
) {
}