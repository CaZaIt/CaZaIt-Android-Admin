/*
package org.cazait.network.interceptor

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import org.cazait.datastore.data.repository.UserPreferenceRepository
import org.cazait.model.local.UserPreference
import org.cazait.network.di.CONTENT_TYPE
import org.cazait.network.di.CONTENT_TYPE_VALUE
import org.cazait.network.dto.response.TokenExpiredExceptionDto
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class AuthInterceptor @Inject constructor(
    private val userPreferenceRepository: UserPreferenceRepository,
    private val ioDispatcher: CoroutineContext,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val user = runBlocking(ioDispatcher) {
            kotlin.runCatching {
                userPreferenceRepository.getUserPreference().first()
            }.getOrDefault(UserPreference.getDefaultInstance())
        }

        // 토큰 적용
        val accessToken = user.accessToken
        val refreshToken = user.refreshToken
        val original = chain.request()
        val request = original.newBuilder()
            .header(CONTENT_TYPE, CONTENT_TYPE_VALUE)
            .header("Authorization", "Bearer $accessToken")
            .header("refreshToken", refreshToken)
            .method(original.method, original.body)
            .build()

        // 요청 처리
        val response = chain.proceed(request)

        if(response.code == 401) {

        }
    }
}
*/
