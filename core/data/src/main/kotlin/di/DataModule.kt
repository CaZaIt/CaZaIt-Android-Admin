package di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import repository.UserRepository
import repository.UserRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl,
    ): UserRepository
}
