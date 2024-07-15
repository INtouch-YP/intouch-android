package care.intouch.app.feature.profile.di

import care.intouch.app.feature.profile.domain.impl.DeleteProfileUseCaseImpl
import care.intouch.app.feature.profile.domain.impl.UpdatePasswordUseCaseImpl
import care.intouch.app.feature.profile.domain.useCase.DeleteProfileUseCase
import care.intouch.app.feature.profile.domain.useCase.UpdatePasswordUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SecurityDomainModule {
    @Binds
    fun bindDeleteProfileUseCase(useCase: DeleteProfileUseCaseImpl): DeleteProfileUseCase
    @Binds
    fun bindUpdatePasswordUseCase(useCase: UpdatePasswordUseCaseImpl): UpdatePasswordUseCase
}