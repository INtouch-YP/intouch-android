package care.intouch.app.feature.authorization.data.di

import care.intouch.app.feature.authorization.domain.api.UserStorage
import care.intouch.app.feature.authorization.data.api.UserRemoteDataSource
import care.intouch.app.feature.authorization.data.impl.UserStorageImpl
import care.intouch.app.feature.authorization.data.impl.UserRemoteDataSourceImpl
import care.intouch.app.feature.authorization.data.impl.UserRepositoryImpl
import care.intouch.app.feature.authorization.domain.api.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UserDataModule {
    @Singleton
    @Binds
    fun bindUserRemoteDataSource(
        impl: UserRemoteDataSourceImpl
    ): UserRemoteDataSource

    @Singleton
    @Binds
    fun bindUserLocalDataSource(
        impl: UserStorageImpl
    ): UserStorage

    @Singleton
    @Binds
    fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository
}