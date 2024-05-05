package care.intouch.app.feature.authorization.pinCode.domain

import care.intouch.app.feature.authorization.pinCode.data.Result

interface IsSetPinCodeUseCase {
    suspend operator fun invoke(): Result<Boolean>
}