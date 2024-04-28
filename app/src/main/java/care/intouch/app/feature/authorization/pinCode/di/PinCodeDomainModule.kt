package care.intouch.app.feature.authorization.pinCode.di

import care.intouch.app.feature.authorization.pinCode.domain.InstallationPinCodeUseCase
import care.intouch.app.feature.authorization.pinCode.domain.ResetPinCodeUseCase
import care.intouch.app.feature.authorization.pinCode.domain.VerificationPinCodeUseCase
import care.intouch.app.feature.authorization.pinCode.domain.impl.InstallationPinCodeUseCaseImpl
import care.intouch.app.feature.authorization.pinCode.domain.impl.ResetPinCodeUseCaseImpl
import care.intouch.app.feature.authorization.pinCode.domain.impl.VerificationPinCodeUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PinCodeDomainModule {

    @Binds
    abstract fun bindInstallationPinCodeUseCase(installationPinCodeUseCaseImpl: InstallationPinCodeUseCaseImpl): InstallationPinCodeUseCase

    @Binds
    abstract fun bindVerificationPinCodeUseCase(verificationPinCodeUseCaseImpl: VerificationPinCodeUseCaseImpl): VerificationPinCodeUseCase

    @Binds
    abstract fun bindResetPinCodeUseCase(resetPinCodeUseCaseImpl: ResetPinCodeUseCaseImpl): ResetPinCodeUseCase
}