package org.bmsk.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.bmsk.domain.model.ManagedCafe
import org.bmsk.domain.repository.StoreRepository
import org.bmsk.domain.repository.UserRepository
import java.util.UUID
import javax.inject.Inject

class StoreMenuUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val storeRepository: StoreRepository
) {
    fun addCafeMenu(
        cafeId: UUID,
        name: String,
        description: String,
        price: Int,
        imageUrl: String
    ): Flow<Result<String>> {
        return storeRepository.addCafeMenu(cafeId, name, description, price, imageUrl)
    }

    fun updateCafeMenu(
        menuId: Long,
        name: String,
        description: String,
        price: Int,
        imageUrl: String
    ): Flow<Result<String>> {
        return storeRepository.updateCafeMenu(menuId, name, description, price, imageUrl)
    }

    suspend fun getManagedCafes(): Flow<Result<List<ManagedCafe>>> {
        val userPreference = userRepository.getCurrentUser().getOrElse {
            userRepository.deleteUserInformation()
        }
        return storeRepository.getManagedCafeList(userPreference.uuid)
    }
}