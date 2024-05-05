package care.intouch.app.feature.authorization.pinCode.domain.impl

import care.intouch.app.feature.authorization.pinCode.data.Result
import care.intouch.app.feature.authorization.pinCode.domain.InstallPinCodeUseCase
import care.intouch.app.feature.authorization.pinCode.domain.PinCodeRepository
import javax.inject.Inject

class InstallPinCodeUseCaseImpl @Inject constructor(private val repository: PinCodeRepository) :
    InstallPinCodeUseCase {
    override suspend fun invoke(pinCode: String): Result<Boolean> =
        repository.installPinCode(pinCode)

}