package care.intouch.app.feature.authorization.pinCode.domain

import care.intouch.app.feature.authorization.pinCode.data.Result

interface ResetPinCodeUseCase {
    suspend operator fun invoke(): Result<Boolean>
}