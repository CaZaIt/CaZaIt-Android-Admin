package org.cazait.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.bmsk.domain.repository.StoreRepository
import org.bmsk.domain.repository.UserRepository
import org.cazait.data.repository.StoreRepositoryImpl
import org.cazait.data.repository.UserRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl,
    ): UserRepository

    @Binds
    fun provideStoreRepository(
        storeRepositoryImpl: StoreRepositoryImpl,
    ): StoreRepository
}
