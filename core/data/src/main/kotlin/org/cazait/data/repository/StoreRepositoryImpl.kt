package org.cazait.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import org.bmsk.domain.DomainResult
import org.bmsk.domain.exception.ErrorType
import org.bmsk.domain.model.CafeImage
import org.bmsk.domain.model.CongestionStatus
import org.bmsk.domain.model.ManagedCafe
import org.bmsk.domain.repository.StoreRepository
import org.cazait.data.caller.ApiCaller
import org.cazait.datastore.data.repository.UserPreferenceRepository
import org.cazait.model.local.UserPreference
import org.cazait.network.datasource.CafeCongestionRemoteData
import org.cazait.network.datasource.CafeSettingRemoteData
import org.cazait.network.dto.request.CafeCreateInRequestBody
import org.cazait.network.dto.request.CafeImageCreateInRequestBody
import org.cazait.network.dto.request.CongestionRequestBody
import org.cazait.network.dto.request.PatchCafeMenuRequestBody
import org.cazait.network.dto.request.PostCafeMenuRequestBody
import org.cazait.network.dto.response.ImageInformationDto
import org.cazait.network.dto.response.ManagedCafeListOutDto
import java.util.UUID
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val userPreferenceRepository: UserPreferenceRepository,
    private val cafeSettingRemoteData: CafeSettingRemoteData,
    private val cafeCongestionRemoteData: CafeCongestionRemoteData,
    private val apiCaller: ApiCaller
) : StoreRepository {
    override suspend fun addCafeBackgroundImage(
        cafeId: Long, imageUrl: List<String>
    ) = apiCaller.safeApiCall {
        cafeSettingRemoteData.postCafeImageUrl(
            CafeImageCreateInRequestBody(
                cafeId, imageUrl
            )
        )
    }

    override suspend fun deleteCafeBackgroundImage(cafeImageId: Long) = apiCaller.safeApiCall {
        val user = getUserPreferenceOrDefault()
        cafeSettingRemoteData.deleteCafeImage(cafeImageId, UUID.fromString(user.id))
    }

    override suspend fun addCafeMenu(
        cafeId: Long, name: String, description: String, price: Int, imageUrl: String
    ) = apiCaller.safeApiCall {
        cafeSettingRemoteData.postCafeMenu(
            cafeId, PostCafeMenuRequestBody(
                cafeId, name, description, price, imageUrl
            )
        )
    }


    override suspend fun updateCafeMenu(
        menuId: Long, name: String?, description: String?, price: Int, imageUrl: String
    ) = apiCaller.safeApiCall {
        cafeSettingRemoteData.patchCafeMenu(
            menuId, PatchCafeMenuRequestBody(menuId, name, description, price, imageUrl)
        )
    }


    override suspend fun deleteCafeMenu(menuId: Long) =
        apiCaller.safeApiCall { cafeSettingRemoteData.deleteCafeMenu(menuId) }

    override suspend fun updateCafeDescription(
        cafeId: Long, cafeName: String, address: String
    ) = apiCaller.safeApiCall {
        val user = getUserPreferenceOrDefault()
        cafeSettingRemoteData.postCafeInformation(
            cafeId, user.uuid, CafeCreateInRequestBody(user.uuid, cafeName, address)
        )
    }

    override suspend fun updateCafeActivation(cafeId: Long) = apiCaller.safeApiCall {
        val user = getUserPreferenceOrDefault()
        cafeSettingRemoteData.postCafeActivation(cafeId, user.uuid)
    }


    override suspend fun resistCafe(name: String, address: String): Flow<DomainResult<String>> {
        val user = getUserPreferenceOrDefault()
        val requestBody = CafeCreateInRequestBody(user.uuid, name, address)

        return apiCaller.safeApiCall {
            cafeSettingRemoteData.postResistCafe(
                user.uuid,
                requestBody
            )
        }
    }

    override suspend fun updateCafeCongestionStatus(
        cafeId: Long, congestionStatus: CongestionStatus
    ): Flow<DomainResult<String>> {
        val user = getUserPreferenceOrDefault()
        val requestBody = CongestionRequestBody(congestionStatus)

        return apiCaller.safeApiCall {
            cafeCongestionRemoteData.postCafeCongestion(
                user.uuid, cafeId, requestBody
            )
        }
    }

    override suspend fun getManagedCafeList(): Flow<DomainResult<List<ManagedCafe>>> {
        val user = getUserPreferenceOrDefault()
        return apiCaller.safeApiCallWithData(
            call = { cafeSettingRemoteData.getManagedCafeList(user.uuid) },
            asDomain = { dto -> dto.asDomain() },
            onNullDataError = ErrorType.NOT_FOUND,
            needServerDescription = true
        )
    }

    private suspend fun getUserPreferenceOrDefault() =
        runCatching { userPreferenceRepository.getUserPreference().first() }.getOrDefault(
            UserPreference.getDefaultInstance()
        )

    private fun List<ManagedCafeListOutDto>.asDomain() = map { it.asDomain() }

    private fun ManagedCafeListOutDto.asDomain() = ManagedCafe(
        cafeId = cafeId,
        name = name,
        address = address,
        cafeImages = cafeImages.map { it.asDomain() }
    )

    private fun ImageInformationDto.asDomain() = CafeImage(
        imageId = imageId,
        url = url
    )
}