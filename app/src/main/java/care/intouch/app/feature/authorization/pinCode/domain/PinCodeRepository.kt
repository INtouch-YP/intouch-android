package care.intouch.app.feature.authorization.pinCode.domain

import care.intouch.app.feature.authorization.pinCode.data.PinCodeState

interface PinCodeRepository {
    suspend fun installPinCode(pinCode: String): PinCodeState
    suspend fun verifyPinCode(pinCode: String): PinCodeState
    suspend fun resetPinCode(): PinCodeState
    suspend fun isSetPinCode(): PinCodeState
    suspend fun skipPinCode(): PinCodeState
}