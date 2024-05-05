package care.intouch.app.feature.authorization.pinCode.di

import care.intouch.app.feature.authorization.pinCode.domain.InstallPinCodeUseCase
import care.intouch.app.feature.authorization.pinCode.domain.IsSetPinCodeUseCase
import care.intouch.app.feature.authorization.pinCode.domain.ResetPinCodeUseCase
import care.intouch.app.feature.authorization.pinCode.domain.VerifyPinCodeUseCase
import care.intouch.app.feature.authorization.pinCode.domain.impl.InstallPinCodeUseCaseImpl
import care.intouch.app.feature.authorization.pinCode.domain.impl.IsSetPinCodeUseCaseImpl
import care.intouch.app.feature.authorization.pinCode.domain.impl.ResetPinCodeUseCaseImpl
import care.intouch.app.feature.authorization.pinCode.domain.impl.VerifyPinCodeUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PinCodeDomainModule {

    @Binds
    abstract fun bindInstallPinCodeUseCase(installPinCodeUseCaseImpl: InstallPinCodeUseCaseImpl): InstallPinCodeUseCase

    @Binds
    abstract fun bindVerifyPinCodeUseCase(verifyPinCodeUseCaseImpl: VerifyPinCodeUseCaseImpl): VerifyPinCodeUseCase

    @Binds
    abstract fun bindResetPinCodeUseCase(resetPinCodeUseCaseImpl: ResetPinCodeUseCaseImpl): ResetPinCodeUseCase

    @Binds
    abstract fun bindIsSetPinCodeUseCase(isSetPinCodeUseCase: IsSetPinCodeUseCaseImpl): IsSetPinCodeUseCase
}