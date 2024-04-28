package care.intouch.app.feature.authorization.pinCode.domain.impl

import care.intouch.app.feature.authorization.pinCode.data.Result
import care.intouch.app.feature.authorization.pinCode.domain.PinCodeRepository
import care.intouch.app.feature.authorization.pinCode.domain.VerificationPinCodeUseCase
import javax.inject.Inject

class VerificationPinCodeUseCaseImpl @Inject constructor(private val repository: PinCodeRepository):
    VerificationPinCodeUseCase {
    override fun invoke(pinCode: String): Result<Boolean> = repository.verificationPinCode(pinCode)
}