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
import org.cazait.network.dto.response.ManagedCafeListOutDto
import java.util.UUID
import javax.inject.Inject

class CafeSettingRemoteData @Inject constructor(
    private val cafeImageService: CafeImageService,
    private val cafeMenuService: CafeMenuService,
    private val cafeService: CafeService
) : CafeSettingRemoteDataSource {
    override fun postCafeImageUrl(cafeImageCreateInRequestBody: CafeImageCreateInRequestBody): Flow<Result<String>> {
        return processCall { cafeImageService.postCafeImageUrl(cafeImageCreateInRequestBody) }
    }

    override fun deleteCafeImage(
        cafeImageId: Long, masterId: UUID
    ): Flow<Result<String>> {
        return processCall { cafeImageService.deleteCafeImage(cafeImageId, masterId) }
    }

    override fun postCafeMenu(
        cafeId: Long, postCafeMenuRequestBody: PostCafeMenuRequestBody
    ): Flow<Result<CafeMenuDto>> {
        return processCall { cafeMenuService.postCafeMenu(cafeId, postCafeMenuRequestBody) }
    }

    override fun deleteCafeMenu(menuId: Long): Flow<Result<String>> {
        return processCall { cafeMenuService.deleteCafeMenu(menuId) }
    }

    override fun patchCafeMenu(
        menuId: Long, patchCafeMenuRequestBody: PatchCafeMenuRequestBody
    ): Flow<Result<CafeMenuDto>> {
        return processCall { cafeMenuService.patchCafeMenu(menuId, patchCafeMenuRequestBody) }
    }

    override fun postCafeInformation(
        cafeId: Long, masterId: UUID, cafeCreateInRequestBody: CafeCreateInRequestBody
    ): Flow<Result<CafeUpdateOutDto>> {
        return processCall {
            cafeService.postCafeInformation(
                cafeId, masterId, cafeCreateInRequestBody
            )
        }
    }

    override fun postCafeActivation(
        cafeId: Long, masterId: UUID
    ): Flow<Result<String>> {
        return processCall { cafeService.postCafeActivation(cafeId, masterId) }
    }

    override fun postResistCafe(
        masterId: UUID, cafeCreateInRequestBody: CafeCreateInRequestBody
    ): Flow<Result<CafeCreateOutDto>> {
        return processCall { cafeService.postResistCafe(masterId, cafeCreateInRequestBody) }
    }

    override fun getManagedCafeList(masterId: UUID): Flow<Result<List<ManagedCafeListOutDto>>> {
        return processCall { cafeService.getManagedCafeList(masterId) }
    }
}