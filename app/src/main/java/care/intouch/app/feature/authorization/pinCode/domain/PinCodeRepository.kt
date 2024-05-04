package care.intouch.app.feature.authorization.pinCode.domain

import care.intouch.app.feature.authorization.pinCode.data.Result

interface PinCodeRepository {
    fun installPinCode(pinCode: String): Result<Boolean>
    fun verifyPinCode(pinCode: String): Result<Boolean>
    fun resetPinCode(): Result<Boolean>
}