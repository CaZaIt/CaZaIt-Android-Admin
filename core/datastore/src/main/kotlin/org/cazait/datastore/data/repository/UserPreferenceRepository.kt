package org.cazait.datastore.data.repository

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.first
import org.cazait.model.local.UserPreference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferenceRepository @Inject constructor(
    private val userPreferenceDataSource: DataStore<UserPreference>
) {
    suspend fun getUserPreference() = runCatching {
        userPreferenceDataSource.data.first()
    }

    suspend fun updateUserPreference(
        isLoggedIn: Boolean, id: String, accountName: String, role: String, accessToken: String, refreshToken: String
    ) = userPreferenceDataSource.updateData { savedUserPreferences ->
        savedUserPreferences.copy(
            isLoggedIn = isLoggedIn, id = id, accountName = accountName, role = role, accessToken = accessToken, refreshToken = refreshToken
        )
    }

    suspend fun updateAccessToken(
        accessToken: String,
    ) = userPreferenceDataSource.updateData { savedUserPreferences ->
        savedUserPreferences.copy(
            accessToken = accessToken,
        )
    }

    suspend fun updateRefreshToken(
        token: String,
    ) = userPreferenceDataSource.updateData { savedUserPreferences ->
        savedUserPreferences.copy(
            refreshToken = token
        )
    }

    suspend fun clearUserPreference() = userPreferenceDataSource.updateData {
        UserPreference.getDefaultInstance()
    }
}