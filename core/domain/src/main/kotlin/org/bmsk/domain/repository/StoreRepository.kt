package org.bmsk.domain.repository

import kotlinx.coroutines.flow.Flow
import org.bmsk.domain.model.CongestionStatus
import org.bmsk.domain.model.ManagedCafe
import java.util.UUID

interface StoreRepository {
    /** cafe image */
    fun addCafeBackgroundImage(
        cafeId: Long,
        imageUrl: List<String>
    ): Flow<Result<String>>

    fun deleteCafeBackgroundImage(cafeImageId: Long): Flow<Result<String>>

    /** cafe menu */
    fun addCafeMenu(
        cafeId: Long,
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
        cafeId: Long,
        cafeName: String,
        address: String
    ): Flow<Result<String>>

    /** cafe activation */
    fun updateCafeActivation(cafeId: Long): Flow<Result<String>>
    fun resistCafe(name: String, address: String): Flow<Result<String>>

    /** cafe congestion */
    fun updateCafeCongestionStatus(
        cafeId: Long,
        congestionStatus: CongestionStatus
    ): Flow<Result<String>>

    /** about managed cafes */
    fun getManagedCafeList(uuid: UUID): Flow<Result<List<ManagedCafe>>>
}