package care.intouch.app.feature.authorization.pinCode.domain

import care.intouch.app.feature.authorization.pinCode.data.Result

interface InstallPinCodeUseCase {
    suspend operator fun invoke(pinCode: String): Result<Boolean>
}