package care.intouch.app.feature.profile.di.profile

import care.intouch.app.feature.profile.data.profile.api.ConfirmEmailChangeRepositoryImpl
import care.intouch.app.feature.profile.data.profile.api.UpdateUserDataRepositoryImpl
import care.intouch.app.feature.profile.data.profile.api.UpdateUserEmailRepositoryImpl
import care.intouch.app.feature.profile.domain.profile.useCase.ConfirmEmailChangeRepository
import care.intouch.app.feature.profile.domain.profile.useCase.ConfirmEmailChangeUseCase
import care.intouch.app.feature.profile.domain.profile.useCase.UpdateUserDataRepository
import care.intouch.app.feature.profile.domain.profile.useCase.UpdateUserDataUseCase
import care.intouch.app.feature.profile.domain.profile.useCase.UpdateUserEmailRepository
import care.intouch.app.feature.profile.domain.profile.useCase.UpdateUserEmailUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ProfileModule {

    @Binds
    fun bindRedactUserDataUseCase(impl: UpdateUserDataUseCase.Base): UpdateUserDataUseCase

    @Binds
    fun bindRedactUserDataPatchRepository(impl: UpdateUserDataRepositoryImpl): UpdateUserDataRepository

    @Binds
    fun bindRedactUserEmailUseCase(impl: UpdateUserEmailUseCase.Base): UpdateUserEmailUseCase

    @Binds
    fun bindRedactUserEmailRepository(impl: UpdateUserEmailRepositoryImpl): UpdateUserEmailRepository

    @Binds
    fun bindConfirmEmailChangeUseCase(impl: ConfirmEmailChangeUseCase.Base): ConfirmEmailChangeUseCase

    @Binds
    fun bindConfirmEmailChangeRepository(impl: ConfirmEmailChangeRepositoryImpl): ConfirmEmailChangeRepository
}

