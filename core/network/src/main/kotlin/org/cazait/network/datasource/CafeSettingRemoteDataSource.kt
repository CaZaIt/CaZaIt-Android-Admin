package org.cazait.network.datasource

import kotlinx.coroutines.flow.Flow
import org.cazait.network.dto.request.CafeCreateInRequestBody
import org.cazait.network.dto.request.CafeImageCreateInRequestBody
import org.cazait.network.dto.request.PatchCafeMenuRequestBody
import org.cazait.network.dto.request.PostCafeMenuRequestBody
import org.cazait.network.dto.response.CafeCreateOutDto
import org.cazait.network.dto.response.CafeMenuDto
import org.cazait.network.dto.response.CafeUpdateOutDto
import org.cazait.network.dto.response.CazaitResponse
import org.cazait.network.dto.response.ManagedCafeListOutDto
import java.util.UUID

interface CafeSettingRemoteDataSource {
    /** image */
    suspend fun postCafeImageUrl(
        cafeImageCreateInRequestBody: CafeImageCreateInRequestBody
    ): Flow<Result<CazaitResponse<String>>>

    suspend fun deleteCafeImage(
        cafeImageId: Long,
        masterId: UUID
    ): Flow<Result<CazaitResponse<String>>>

    /** menu */
    suspend fun postCafeMenu(
        cafeId: Long,
        postCafeMenuRequestBody: PostCafeMenuRequestBody
    ): Flow<Result<CazaitResponse<CafeMenuDto>>>

    suspend fun deleteCafeMenu(
        menuId: Long
    ): Flow<Result<CazaitResponse<String>>>

    suspend fun patchCafeMenu(
        menuId: Long,
        patchCafeMenuRequestBody: PatchCafeMenuRequestBody
    ): Flow<Result<CazaitResponse<CafeMenuDto>>>

    /** cafe information */
    suspend fun postCafeInformation(
        cafeId: Long,
        masterId: UUID,
        cafeCreateInRequestBody: CafeCreateInRequestBody
    ): Flow<Result<CazaitResponse<CafeUpdateOutDto>>>

    suspend fun postCafeActivation(
        cafeId: Long,
        masterId: UUID
    ): Flow<Result<CazaitResponse<String>>>

    suspend fun postResistCafe(
        masterId: UUID,
        cafeCreateInRequestBody: CafeCreateInRequestBody
    ): Flow<Result<CazaitResponse<CafeCreateOutDto>>>

    /** managed cafes */
    suspend fun getManagedCafeList(
        masterId: UUID
    ): Flow<Result<CazaitResponse<List<ManagedCafeListOutDto>>>>
}