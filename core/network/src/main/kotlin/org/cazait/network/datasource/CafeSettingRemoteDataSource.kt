package org.cazait.network.datasource

import kotlinx.coroutines.flow.Flow
import org.cazait.network.dto.request.CafeCreateInRequestBody
import org.cazait.network.dto.request.CafeImageCreateInRequestBody
import org.cazait.network.dto.request.PatchCafeMenuRequestBody
import org.cazait.network.dto.request.PostCafeMenuRequestBody
import org.cazait.network.dto.response.CafeCreateOutDto
import org.cazait.network.dto.response.CafeMenuDto
import org.cazait.network.dto.response.CafeUpdateOutDto
import org.cazait.network.dto.response.ManagedCafeListOutDto
import java.util.UUID

interface CafeSettingRemoteDataSource {
    /** image */
    fun postCafeImageUrl(
        cafeImageCreateInRequestBody: CafeImageCreateInRequestBody
    ): Flow<Result<String>>

    fun deleteCafeImage(
        cafeImageId: Long,
        masterId: UUID
    ): Flow<Result<String>>

    /** menu */
    fun postCafeMenu(
        cafeId: Long,
        postCafeMenuRequestBody: PostCafeMenuRequestBody
    ): Flow<Result<CafeMenuDto>>

    fun deleteCafeMenu(
        menuId: Long
    ): Flow<Result<String>>

    fun patchCafeMenu(
        menuId: Long,
        patchCafeMenuRequestBody: PatchCafeMenuRequestBody
    ): Flow<Result<CafeMenuDto>>

    /** cafe information */
    fun postCafeInformation(
        cafeId: Long,
        masterId: UUID,
        cafeCreateInRequestBody: CafeCreateInRequestBody
    ): Flow<Result<CafeUpdateOutDto>>

    fun postCafeActivation(
        cafeId: Long,
        masterId: UUID
    ): Flow<Result<String>>

    fun postResistCafe(
        masterId: UUID,
        cafeCreateInRequestBody: CafeCreateInRequestBody
    ): Flow<Result<CafeCreateOutDto>>

    /** managed cafes */
    fun getManagedCafeList(
        masterId: UUID
    ): Flow<Result<List<ManagedCafeListOutDto>>>
}