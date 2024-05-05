package care.intouch.app.feature.authorization.pinCode.domain

import care.intouch.app.feature.authorization.pinCode.data.Result

interface VerifyPinCodeUseCase {
    suspend operator fun invoke(pinCode: String): Result<Boolean>
}