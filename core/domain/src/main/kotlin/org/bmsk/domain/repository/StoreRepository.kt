package org.bmsk.domain.repository

interface StoreRepository {
    suspend fun addCafeBackgroundImage()
    suspend fun deleteCafeBackgroundImage()
    suspend fun addCafeMenu()
    suspend fun updateCafeMenu()
    suspend fun updateCafeDescription()
}