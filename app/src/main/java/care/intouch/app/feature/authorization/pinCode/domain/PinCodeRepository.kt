package care.intouch.app.feature.authorization.pinCode.domain

import care.intouch.app.feature.authorization.pinCode.data.Result

interface PinCodeRepository {
    fun installationPinCode(pinCode: String): Result<Boolean>
    fun verificationPinCode(pinCode: String): Result<Boolean>
    fun resetPinCode(): Result<Boolean>
}