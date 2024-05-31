package care.intouch.app.feature.authorization.domain.di

import care.intouch.app.feature.authorization.domain.useCase.GetUserNameUseCase
import care.intouch.app.feature.authorization.domain.useCase.ResetPasswordUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UserDomainModule {
    @Binds
    fun bindGetUserNameUseCase(impl: GetUserNameUseCase.Base): GetUserNameUseCase

    @Binds
    fun bindResetPasswordUseCase(impl: ResetPasswordUseCase.Base): ResetPasswordUseCase
}