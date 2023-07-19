package org.cazait.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.bmsk.domain.DomainResult
import org.bmsk.domain.exception.DomainError
import org.bmsk.domain.model.CongestionStatus
import org.bmsk.domain.repository.StoreRepository
import org.cazait.datastore.data.repository.UserPreferenceRepository
import org.cazait.model.local.UserPreference
import org.cazait.network.datasource.CafeCongestionRemoteData
import org.cazait.network.datasource.CafeSettingRemoteData
import org.cazait.network.dto.request.CafeCreateInRequestBody
import org.cazait.network.dto.request.CafeImageCreateInRequestBody
import org.cazait.network.dto.request.CongestionRequestBody
import org.cazait.network.dto.request.PatchCafeMenuRequestBody
import org.cazait.network.dto.request.PostCafeMenuRequestBody
import org.cazait.network.dto.response.CazaitResponse
import java.io.IOException
import java.util.UUID
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class StoreRepositoryImpl @Inject constructor(
    private val userPreferenceRepository: UserPreferenceRepository,
    private val cafeSettingRemoteData: CafeSettingRemoteData,
    private val cafeCongestionRemoteData: CafeCongestionRemoteData,
    private val ioDispatcher: CoroutineContext
) : StoreRepository {
    override suspend fun addCafeBackgroundImage(
        cafeId: Long, imageUrl: List<String>
    ) = safeApiCall {
        cafeSettingRemoteData.postCafeImageUrl(
            CafeImageCreateInRequestBody(
                cafeId, imageUrl
            )
        )
    }

    override suspend fun deleteCafeBackgroundImage(cafeImageId: Long) = safeApiCall {
        val user = getUserPreferenceOrDefault()
        cafeSettingRemoteData.deleteCafeImage(cafeImageId, UUID.fromString(user.id))
    }

    override suspend fun addCafeMenu(
        cafeId: Long, name: String, description: String, price: Int, imageUrl: String
    ) = safeApiCall {
        cafeSettingRemoteData.postCafeMenu(
            cafeId, PostCafeMenuRequestBody(
                cafeId, name, description, price, imageUrl
            )
        )
    }


    override suspend fun updateCafeMenu(
        menuId: Long, name: String?, description: String?, price: Int, imageUrl: String
    ) = safeApiCall {
        cafeSettingRemoteData.patchCafeMenu(
            menuId, PatchCafeMenuRequestBody(menuId, name, description, price, imageUrl)
        )
    }


    override suspend fun deleteCafeMenu(menuId: Long) =
        safeApiCall { cafeSettingRemoteData.deleteCafeMenu(menuId) }

    override suspend fun updateCafeDescription(
        cafeId: Long, cafeName: String, address: String
    ) = safeApiCall {
        val user = getUserPreferenceOrDefault()
        cafeSettingRemoteData.postCafeInformation(
            cafeId, user.uuid, CafeCreateInRequestBody(user.uuid, cafeName, address)
        )
    }

    override suspend fun updateCafeActivation(cafeId: Long) = safeApiCall {
        val user = getUserPreferenceOrDefault()
        cafeSettingRemoteData.postCafeActivation(cafeId, user.uuid)
    }


    override suspend fun resistCafe(name: String, address: String): Flow<DomainResult<String>> {
        val user = getUserPreferenceOrDefault()
        val requestBody = CafeCreateInRequestBody(user.uuid, name, address)

        return safeApiCall { cafeSettingRemoteData.postResistCafe(user.uuid, requestBody) }
    }

    override suspend fun updateCafeCongestionStatus(
        cafeId: Long, congestionStatus: CongestionStatus
    ): Flow<DomainResult<String>> {
        val user = getUserPreferenceOrDefault()
        val requestBody = CongestionRequestBody(congestionStatus)

        return safeApiCall {
            cafeCongestionRemoteData.postCafeCongestion(
                user.uuid, cafeId, requestBody
            )
        }
    }

    private suspend fun getUserPreferenceOrDefault() =
        runCatching { userPreferenceRepository.getUserPreference().first() }.getOrDefault(
            UserPreference.getDefaultInstance()
        )

    private suspend fun safeApiCall(
        call: suspend () -> Flow<Result<CazaitResponse<*>>>
    ): Flow<DomainResult<String>> {
        return flow<DomainResult<String>> {
            call().first()
                .onFailure { exception ->
                    when (exception) {
                        is IOException -> emit(DomainResult.Error(DomainError.NetworkError(null)))
                        else -> emit(DomainResult.Error(DomainError.UnKnownError(null)))
                    }
                }
                .onSuccess {
                    if (it.data == null) {
                        emit(DomainResult.Error(DomainError.InvalidInputError(it.message)))
                    } else {
                        emit(DomainResult.Success(it.message))
                    }
                }

        }.flowOn(ioDispatcher)
    }
}