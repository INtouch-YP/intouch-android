package care.intouch.app.feature.authorization.pinCode.di

import care.intouch.app.feature.authorization.pinCode.data.PinCodeRepository
import care.intouch.app.feature.authorization.pinCode.data.PinCodeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PinCodeRepositoryModule {
    @Binds
    abstract fun bindPinCodeRepository(pinCodeRepository: PinCodeRepositoryImpl): PinCodeRepository
}