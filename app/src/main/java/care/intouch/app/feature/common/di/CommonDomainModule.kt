package care.intouch.app.feature.common.di

import care.intouch.app.feature.common.domain.useCase.LogOutUseCase
import care.intouch.app.feature.common.domain.useCase.UpdateUserProfileCacheUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface CommonDomainModule {
    @Binds
    fun bindUpdateUserProfileCacheUseCase(impl: UpdateUserProfileCacheUseCase.Base): UpdateUserProfileCacheUseCase

    @Binds
    fun bindLogOutUseCase(impl: LogOutUseCase.Base): LogOutUseCase
}