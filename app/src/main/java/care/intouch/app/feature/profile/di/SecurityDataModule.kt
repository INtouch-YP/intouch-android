package care.intouch.app.feature.profile.di

import care.intouch.app.feature.profile.data.api.DeleteProfileRepository
import care.intouch.app.feature.profile.data.api.UpdatePasswordRepository
import care.intouch.app.feature.profile.data.impl.DeleteProfileRepositoryImpl
import care.intouch.app.feature.profile.data.impl.UpdatePasswordRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SecurityDataModule {
    @Binds
    @Singleton
    fun bindDeleteProfileRepository(impl: DeleteProfileRepositoryImpl): DeleteProfileRepository

    @Binds
    @Singleton
    fun bindUpdatePasswordRepository(impl: UpdatePasswordRepositoryImpl): UpdatePasswordRepository
}