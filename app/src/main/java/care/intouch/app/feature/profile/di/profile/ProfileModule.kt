package care.intouch.app.feature.profile.di.profile

import care.intouch.app.feature.profile.data.profile.api.RedactUserDataPatchRepositoryImpl
import care.intouch.app.feature.profile.data.profile.api.RedactUserEmailRepositoryImpl
import care.intouch.app.feature.profile.domain.profile.useCase.RedactUserDataPatchRepository
import care.intouch.app.feature.profile.domain.profile.useCase.RedactUserDataUseCase
import care.intouch.app.feature.profile.domain.profile.useCase.RedactUserEmailRepository
import care.intouch.app.feature.profile.domain.profile.useCase.RedactUserEmailUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ProfileModule {

    @Binds
    fun bindRedactUserDataUseCase(impl: RedactUserDataUseCase.Base): RedactUserDataUseCase

    @Binds
    fun bindRedactUserDataPatchRepository(impl: RedactUserDataPatchRepositoryImpl): RedactUserDataPatchRepository

    @Binds
    fun bindRedactUserEmailUseCase(impl: RedactUserEmailUseCase.Base): RedactUserEmailUseCase

    @Binds
    fun bindRedactUserEmailRepository(impl: RedactUserEmailRepositoryImpl): RedactUserEmailRepository

}

