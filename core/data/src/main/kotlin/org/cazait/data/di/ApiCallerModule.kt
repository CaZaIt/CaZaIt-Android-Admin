package org.cazait.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.cazait.data.caller.ApiCaller
import org.cazait.data.caller.ApiCallerImpl

@Module
@InstallIn(SingletonComponent::class)
interface ApiCallerModule {
    @Binds
    fun provideApiCaller(
        apiCallerImpl: ApiCallerImpl
    ): ApiCaller
}