package care.intouch.app.feature.authorization.pinCode.domain

import care.intouch.app.feature.authorization.pinCode.data.Result

interface PinCodeRepository {
    suspend fun installPinCode(pinCode: String): Result<Boolean>
    suspend fun verifyPinCode(pinCode: String): Result<Boolean>
    suspend fun resetPinCode(): Result<Boolean>
    suspend fun isSetPinCode(): Result<Boolean>
}