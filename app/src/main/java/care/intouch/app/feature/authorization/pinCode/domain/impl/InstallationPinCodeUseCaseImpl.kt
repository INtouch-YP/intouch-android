package care.intouch.app.feature.authorization.pinCode.domain.impl

import care.intouch.app.feature.authorization.pinCode.data.Result
import care.intouch.app.feature.authorization.pinCode.domain.InstallationPinCodeUseCase
import care.intouch.app.feature.authorization.pinCode.domain.PinCodeRepository
import javax.inject.Inject

class InstallationPinCodeUseCaseImpl @Inject constructor(private val repository: PinCodeRepository) :
    InstallationPinCodeUseCase {
    override fun invoke(pinCode: String): Result<Boolean> = repository.installationPinCode(pinCode)

}