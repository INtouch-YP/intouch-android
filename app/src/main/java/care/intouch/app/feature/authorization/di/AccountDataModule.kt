package care.intouch.app.feature.authorization.di


import care.intouch.app.feature.authorization.data.api.AccountLocalDataSource
import care.intouch.app.feature.authorization.data.impl.AccountLocalDataSourceImpl
import care.intouch.app.feature.authorization.data.impl.AccountStateRepositoryImpl
import care.intouch.app.feature.authorization.domain.api.AccountStateRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface AccountDataModule {
    @Singleton
    @Binds
    fun bindAccountLocalDataSource(
        impl: AccountLocalDataSourceImpl,
    ): AccountLocalDataSource

    @Singleton
    @Binds
    fun bindAccountStateRepository(
        impl: AccountStateRepositoryImpl,
    ): AccountStateRepository
}