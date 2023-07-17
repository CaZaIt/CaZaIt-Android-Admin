package org.cazait.network.datasource

import kotlinx.coroutines.flow.Flow
import org.cazait.network.api.CafeImageService
import org.cazait.network.api.CafeMenuService
import org.cazait.network.api.CafeService
import org.cazait.network.dto.request.CafeCreateInRequestBody
import org.cazait.network.dto.request.CafeImageCreateInRequestBody
import org.cazait.network.dto.request.PatchCafeMenuRequestBody
import org.cazait.network.dto.request.PostCafeMenuRequestBody
import org.cazait.network.dto.response.CafeCreateOutDto
import org.cazait.network.dto.response.CafeMenuDto
import org.cazait.network.dto.response.CafeUpdateOutDto
import org.cazait.network.dto.response.CazaitResponse
import java.util.UUID
import javax.inject.Inject

class CafeSettingRemoteData @Inject constructor(
    private val cafeImageService: CafeImageService,
    private val cafeMenuService: CafeMenuService,
    private val cafeService: CafeService
) : CafeSettingRemoteDataSource {
    override suspend fun postCafeImageUrl(cafeImageCreateInRequestBody: CafeImageCreateInRequestBody): Flow<Result<CazaitResponse<String>>> {
        return processCall { cafeImageService.postCafeImageUrl(cafeImageCreateInRequestBody) }
    }

    override suspend fun deleteCafeImage(
        cafeImageId: Long, masterId: UUID
    ): Flow<Result<CazaitResponse<String>>> {
        return processCall { cafeImageService.deleteCafeImage(cafeImageId, masterId) }
    }

    override suspend fun postCafeMenu(
        cafeId: Long, postCafeMenuRequestBody: PostCafeMenuRequestBody
    ): Flow<Result<CazaitResponse<CafeMenuDto>>> {
        return processCall { cafeMenuService.postCafeMenu(cafeId, postCafeMenuRequestBody) }
    }

    override suspend fun deleteCafeMenu(menuId: Long): Flow<Result<CazaitResponse<String>>> {
        return processCall { cafeMenuService.deleteCafeMenu(menuId) }
    }

    override suspend fun patchCafeMenu(
        menuId: Long, patchCafeMenuRequestBody: PatchCafeMenuRequestBody
    ): Flow<Result<CazaitResponse<CafeMenuDto>>> {
        return processCall { cafeMenuService.patchCafeMenu(menuId, patchCafeMenuRequestBody) }
    }

    override suspend fun postCafeInformation(
        cafeId: Long, masterId: UUID, cafeCreateInRequestBody: CafeCreateInRequestBody
    ): Flow<Result<CazaitResponse<CafeUpdateOutDto>>> {
        return processCall {
            cafeService.postCafeInformation(
                cafeId, masterId, cafeCreateInRequestBody
            )
        }
    }

    override suspend fun postCafeActivation(
        cafeId: Long, masterId: UUID
    ): Flow<Result<CazaitResponse<String>>> {
        return processCall { cafeService.postCafeActivation(cafeId, masterId) }
    }

    override suspend fun postResistCafe(
        masterId: UUID, cafeCreateInRequestBody: CafeCreateInRequestBody
    ): Flow<Result<CazaitResponse<CafeCreateOutDto>>> {
        return processCall { cafeService.postResistCafe(masterId, cafeCreateInRequestBody) }
    }
}