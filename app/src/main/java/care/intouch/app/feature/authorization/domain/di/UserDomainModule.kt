package care.intouch.app.feature.authorization.domain.di

import care.intouch.app.feature.authorization.domain.useCase.GetUserNameUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UserDomainModule {
    @Binds
    fun bindGetUserNameUseCase(impl: GetUserNameUseCase.Base): GetUserNameUseCase
}