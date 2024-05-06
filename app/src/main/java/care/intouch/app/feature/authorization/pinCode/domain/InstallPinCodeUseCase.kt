package care.intouch.app.feature.authorization.pinCode.domain

import care.intouch.app.feature.authorization.pinCode.data.PinCodeState

interface InstallPinCodeUseCase {
    suspend operator fun invoke(pinCode: String): PinCodeState
}