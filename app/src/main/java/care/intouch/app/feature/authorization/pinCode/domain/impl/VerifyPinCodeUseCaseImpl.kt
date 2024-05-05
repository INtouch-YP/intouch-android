package care.intouch.app.feature.authorization.pinCode.domain.impl

import care.intouch.app.feature.authorization.pinCode.data.Result
import care.intouch.app.feature.authorization.pinCode.domain.PinCodeRepository
import care.intouch.app.feature.authorization.pinCode.domain.VerifyPinCodeUseCase
import javax.inject.Inject

class VerifyPinCodeUseCaseImpl @Inject constructor(private val repository: PinCodeRepository) :
    VerifyPinCodeUseCase {
    override suspend fun invoke(pinCode: String): Result<Boolean> =
        repository.verifyPinCode(pinCode)
}