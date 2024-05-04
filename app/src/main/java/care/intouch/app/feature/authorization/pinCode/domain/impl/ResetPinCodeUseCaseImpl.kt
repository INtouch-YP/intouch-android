package care.intouch.app.feature.authorization.pinCode.domain.impl

import care.intouch.app.feature.authorization.pinCode.data.Result
import care.intouch.app.feature.authorization.pinCode.domain.PinCodeRepository
import care.intouch.app.feature.authorization.pinCode.domain.ResetPinCodeUseCase
import javax.inject.Inject

class ResetPinCodeUseCaseImpl  @Inject constructor(private val repository: PinCodeRepository) :
    ResetPinCodeUseCase {
    override fun invoke(): Result<Boolean> = repository.resetPinCode()

}