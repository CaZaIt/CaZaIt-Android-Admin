package org.bmsk.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.bmsk.domain.DomainResult
import org.bmsk.domain.repository.StoreRepository
import javax.inject.Inject

class StoreUseCase @Inject constructor(
    private val storeRepository: StoreRepository
) {
    suspend fun addCafeMenu(
        cafeId: Long,
        name: String,
        description: String,
        price: Int,
        imageUrl: String
    ): Flow<DomainResult<String>> {
        return storeRepository.addCafeMenu(cafeId, name, description, price, imageUrl)
    }

    suspend fun updateCafeMenu(
        menuId: Long,
        name: String,
        description: String,
        price: Int,
        imageUrl: String
    ): Flow<DomainResult<String>> {
        return storeRepository.updateCafeMenu(menuId, name, description, price, imageUrl)
    }

    suspend fun getManagedCafes() = storeRepository.getManagedCafeList()
}