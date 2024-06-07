package care.intouch.app.feature.authorization.pinCode.data

import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity

interface PinCodeRepository {
    suspend fun installPinCode(pinCode: String): Resource<Boolean, ErrorEntity>
    suspend fun verifyPinCode(pinCode: String): Resource<Boolean, ErrorEntity>
    suspend fun resetPinCode(): Resource<Boolean, ErrorEntity>
    suspend fun isPinCodeSet(): Resource<Boolean, ErrorEntity>
    suspend fun skipPinCode(): Resource<Boolean, ErrorEntity>
}