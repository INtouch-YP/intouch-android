package care.intouch.app.feature.authorization.pinCode.di

import care.intouch.app.feature.authorization.pinCode.data.PinCodeRepositoryImpl
import care.intouch.app.feature.authorization.pinCode.domain.PinCodeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PinCodeRepositoryModule {
    @Binds
    abstract fun bindPinCodeRepository(pinCodeRepository: PinCodeRepositoryImpl): PinCodeRepository
}