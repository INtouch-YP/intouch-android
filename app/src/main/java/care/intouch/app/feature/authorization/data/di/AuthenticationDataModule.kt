package care.intouch.app.feature.authorization.data.di

import care.intouch.app.feature.authorization.data.api.AuthenticationRemoteDataSource
import care.intouch.app.feature.authorization.data.impl.AuthenticationRemoteDataSourceImpl
import care.intouch.app.feature.authorization.data.impl.AuthenticationRepositoryImpl
import care.intouch.app.feature.authorization.domain.api.AuthenticationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AuthenticationDataModule {
    @Singleton
    @Binds
    fun bindAuthenticationRemoteDataSource(
        impl: AuthenticationRemoteDataSourceImpl
    ): AuthenticationRemoteDataSource

    @Singleton
    @Binds
    fun bindAuthenticationRepository(
        impl: AuthenticationRepositoryImpl
    ): AuthenticationRepository
}