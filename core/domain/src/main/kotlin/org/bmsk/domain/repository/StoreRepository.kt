package org.bmsk.domain.repository

import kotlinx.coroutines.flow.Flow
import org.bmsk.domain.model.CongestionStatus
import org.bmsk.domain.model.ManagedCafe
import java.util.UUID

interface StoreRepository {
    /** cafe image */
    fun addCafeBackgroundImage(
        cafeId: UUID,
        imageUrl: List<String>
    ): Flow<Result<String>>

    fun deleteCafeBackgroundImage(cafeImageId: Long): Flow<Result<String>>

    /** cafe menu */
    fun addCafeMenu(
        cafeId: UUID,
        name: String,
        description: String,
        price: Int,
        imageUrl: String
    ): Flow<Result<String>>

    fun updateCafeMenu(
        menuId: Long,
        name: String?,
        description: String?,
        price: Int,
        imageUrl: String
    ): Flow<Result<String>>

    fun deleteCafeMenu(
        menuId: Long
    ): Flow<Result<String>>

    /** cafe description */
    fun updateCafeDescription(
        cafeId: UUID,
        cafeName: String,
        address: String
    ): Flow<Result<String>>

    /** cafe activation */
    fun updateCafeActivation(cafeId: UUID): Flow<Result<String>>
    fun resistCafe(name: String, address: String): Flow<Result<String>>

    /** cafe congestion */
    fun updateCafeCongestionStatus(
        cafeId: UUID,
        congestionStatus: CongestionStatus
    ): Flow<Result<String>>

    /** about managed cafes */
    fun getManagedCafeList(uuid: UUID): Flow<Result<List<ManagedCafe>>>
}