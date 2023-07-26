package org.cazait.network.di

import org.cazait.network.api.AuthService
import org.cazait.network.api.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.cazait.network.annotation.Authenticated
import org.cazait.network.api.CafeImageService
import org.cazait.network.api.CafeMenuService
import org.cazait.network.api.CafeService
import org.cazait.network.api.CongestionService
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providesAuthService(
        @Authenticated retrofit: Retrofit
    ): AuthService = retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun providesUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun providesCafeImageService(
        @Authenticated retrofit: Retrofit
    ): CafeImageService = retrofit.create(CafeImageService::class.java)

    @Provides
    @Singleton
    fun providesCafeMenuService(
        @Authenticated retrofit: Retrofit
    ): CafeMenuService = retrofit.create(CafeMenuService::class.java)

    @Provides
    @Singleton
    fun providesCafeService(
        @Authenticated retrofit: Retrofit
    ): CafeService = retrofit.create(CafeService::class.java)

    @Provides
    @Singleton
    fun providesCongestionService(
        @Authenticated retrofit: Retrofit
    ): CongestionService = retrofit.create(CongestionService::class.java)
}
