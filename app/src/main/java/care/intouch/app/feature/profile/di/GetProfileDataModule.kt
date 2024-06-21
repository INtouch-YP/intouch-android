package care.intouch.app.feature.profile.di

import care.intouch.app.feature.profile.data.GetProfileDataRepositoryImpl
import care.intouch.app.feature.profile.domain.repository.GetProfileDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface GetProfileDataModule {
    @Singleton
    @Binds
    fun bindGetProfileDataRepository(
        impl: GetProfileDataRepositoryImpl
    ): GetProfileDataRepository
}



