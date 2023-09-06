package org.cazait.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.bmsk.domain.model.CafeImage
import org.bmsk.domain.model.CongestionStatus
import org.bmsk.domain.model.ManagedCafe
import org.bmsk.domain.repository.StoreRepository
import org.bmsk.domain.repository.UserRepository
import org.cazait.network.datasource.CafeCongestionRemoteData
import org.cazait.network.datasource.CafeSettingRemoteData
import org.cazait.network.dto.request.CongestionRequestBody
import org.cazait.network.dto.response.CongestionUpdateOutDto
import org.cazait.network.dto.response.ManagedCafeListOutDto
import java.util.UUID
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val cafeSettingRemoteData: CafeSettingRemoteData,
    private val cafeCongestionRemoteData: CafeCongestionRemoteData,
) : StoreRepository {

    override fun addCafeBackgroundImage(cafeId: UUID, imageUrl: List<String>): Flow<Result<String>> {
        TODO("Not yet implemented")
    }

    override fun deleteCafeBackgroundImage(cafeImageId: Long): Flow<Result<String>> {
        TODO("Not yet implemented")
    }

    override fun addCafeMenu(cafeId: UUID, name: String, description: String, price: Int, imageUrl: String): Flow<Result<String>> {
        TODO("Not yet implemented")
    }

    override fun updateCafeMenu(menuId: Long, name: String?, description: String?, price: Int, imageUrl: String): Flow<Result<String>> {
        TODO("Not yet implemented")
    }

    override fun deleteCafeMenu(menuId: Long): Flow<Result<String>> {
        TODO("Not yet implemented")
    }

    override fun updateCafeDescription(cafeId: UUID, cafeName: String, address: String): Flow<Result<String>> {
        TODO("Not yet implemented")
    }

    override fun updateCafeActivation(cafeId: UUID): Flow<Result<String>> {
        TODO("Not yet implemented")
    }

    override fun resistCafe(name: String, address: String): Flow<Result<String>> {
        TODO("Not yet implemented")
    }

    override fun updateCafeCongestionStatus(userId: UUID, cafeId: UUID, congestionStatus: CongestionStatus): Flow<Result<String>> {
        return cafeCongestionRemoteData.postCafeCongestion(
            masterId = userId,
            cafeId = cafeId,
            requestBody = CongestionRequestBody(congestionStatus)
        ).map {
            it.mapCatching { dto ->
                dto.asDomain()
            }
        }
    }


    override fun getManagedCafeList(uuid: UUID): Flow<Result<List<ManagedCafe>>> {
        return cafeSettingRemoteData.getManagedCafeList(uuid).map {
            it.mapCatching { list ->
                list.asDomain()
            }
        }
    }
}

private fun List<ManagedCafeListOutDto>.asDomain() = map { dto ->
    ManagedCafe(
        dto.cafeId,
        dto.name,
        dto.address,
        cafeImages = dto.cafeImages.map { CafeImage(it.imageId, it.url) }
    )
}

private fun CongestionUpdateOutDto.asDomain() = congestionStatus.name