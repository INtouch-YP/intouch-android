package care.intouch.app.feature.authorization.pinCode.domain

import care.intouch.app.feature.authorization.pinCode.data.PinCodeState

interface IsSetPinCodeUseCase {
    suspend operator fun invoke(): PinCodeState
}