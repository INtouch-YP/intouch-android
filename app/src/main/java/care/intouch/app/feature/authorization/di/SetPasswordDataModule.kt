package care.intouch.app.feature.authorization.di

import care.intouch.app.feature.authorization.data.impl.PasswordRepositoryImpl
import care.intouch.app.feature.authorization.domain.api.PasswordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SetPasswordDataModule {
    @Binds
    fun bindPasswordRepository(impl: PasswordRepositoryImpl): PasswordRepository
}