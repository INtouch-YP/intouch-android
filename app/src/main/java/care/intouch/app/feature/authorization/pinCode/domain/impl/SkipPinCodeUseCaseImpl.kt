package care.intouch.app.feature.authorization.pinCode.domain.impl

import care.intouch.app.feature.authorization.pinCode.data.PinCodeState
import care.intouch.app.feature.authorization.pinCode.domain.PinCodeRepository
import care.intouch.app.feature.authorization.pinCode.domain.SkipPinCodeUseCase
import javax.inject.Inject

class SkipPinCodeUseCaseImpl @Inject constructor(private val repository: PinCodeRepository) :
    SkipPinCodeUseCase {
    override suspend fun invoke(): PinCodeState = repository.resetPinCode()

}