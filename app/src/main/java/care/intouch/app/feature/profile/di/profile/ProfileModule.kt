package care.intouch.app.feature.profile.di.profile

import care.intouch.app.feature.profile.domain.profile.useCase.RedactUserDataUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ProfileModule {

    @Binds
    fun bindRedactUserDataUseCase(impl: RedactUserDataUseCase.Base): RedactUserDataUseCase
}

