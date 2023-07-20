package org.bmsk.domain.repository

import kotlinx.coroutines.flow.Flow
import org.bmsk.domain.DomainResult
import org.bmsk.domain.model.CongestionStatus
import org.bmsk.domain.model.ManagedCafe

interface StoreRepository {
    /** cafe image */
    suspend fun addCafeBackgroundImage(
        cafeId: Long,
        imageUrl: List<String>
    ): Flow<DomainResult<String>>

    suspend fun deleteCafeBackgroundImage(cafeImageId: Long): Flow<DomainResult<String>>

    /** cafe menu */
    suspend fun addCafeMenu(
        cafeId: Long,
        name: String,
        description: String,
        price: Int,
        imageUrl: String
    ): Flow<DomainResult<String>>

    suspend fun updateCafeMenu(
        menuId: Long,
        name: String?,
        description: String?,
        price: Int,
        imageUrl: String
    ): Flow<DomainResult<String>>

    suspend fun deleteCafeMenu(
        menuId: Long
    ): Flow<DomainResult<String>>

    /** cafe description */
    suspend fun updateCafeDescription(
        cafeId: Long,
        cafeName: String,
        address: String
    ): Flow<DomainResult<String>>

    /** cafe activation */
    suspend fun updateCafeActivation(cafeId: Long): Flow<DomainResult<String>>
    suspend fun resistCafe(name: String, address: String): Flow<DomainResult<String>>

    /** cafe congestion */
    suspend fun updateCafeCongestionStatus(
        cafeId: Long,
        congestionStatus: CongestionStatus
    ): Flow<DomainResult<String>>

    /** about managed cafes */
    suspend fun getManagedCafeList(): Flow<DomainResult<List<ManagedCafe>>>
}