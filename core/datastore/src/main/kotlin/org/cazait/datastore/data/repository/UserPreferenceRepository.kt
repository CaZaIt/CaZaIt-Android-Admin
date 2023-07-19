package org.cazait.datastore.data.repository

import androidx.datastore.core.DataStore
import org.cazait.model.local.UserPreference
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferenceRepository @Inject constructor(
    private val userPreferenceDataSource: DataStore<UserPreference>
) {
    fun getUserPreference() = userPreferenceDataSource.data

    suspend fun updateUserPreference(
        isLoggedIn: Boolean,
        id: String,
        loginId: String,
        role: String,
        accessToken: String,
        refreshToken: String
    ) {
        userPreferenceDataSource.updateData { savedUserPreferences ->
            savedUserPreferences.copy(
                isLoggedIn = isLoggedIn,
                id = id,
                loginId = loginId,
                role = role,
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        }
    }

    suspend fun updateUserToken(
        accessToken: String,
        refreshToken: String,
    ) {
        userPreferenceDataSource.updateData { savedUserPreferences ->
            savedUserPreferences.copy(
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        }
    }

    suspend fun updateUserToken(
        token: String,
        updateMode: Int,
    ) {
        if (updateMode == UPDATE_ACCESS_TOKEN) {
            userPreferenceDataSource.updateData { savedUserPreferences ->
                savedUserPreferences.copy(
                    accessToken = token
                )
            }
        } else if (updateMode == UPDATE_REFRESH_TOKEN) {
            userPreferenceDataSource.updateData { savedUserPreferences ->
                savedUserPreferences.copy(
                    refreshToken = token
                )
            }
        }
    }

    suspend fun clearUserPreference() {
        userPreferenceDataSource.updateData {
            UserPreference.getDefaultInstance()
        }
    }

    companion object TokenType {
        const val UPDATE_ACCESS_TOKEN = 1
        const val UPDATE_REFRESH_TOKEN = 2
    }
}