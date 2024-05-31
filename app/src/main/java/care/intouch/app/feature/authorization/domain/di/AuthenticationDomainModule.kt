package care.intouch.app.feature.authorization.domain.di

import care.intouch.app.feature.authorization.domain.useCase.ConfirmEmailUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface AuthenticationDomainModule {
    @Binds
    fun bindGetConfirmEmailUseCase(impl: ConfirmEmailUseCase.Base): ConfirmEmailUseCase
}