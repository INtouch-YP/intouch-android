package care.intouch.app.feature.authorization.pinCode.domain.impl

import care.intouch.app.feature.authorization.pinCode.data.Result
import care.intouch.app.feature.authorization.pinCode.domain.IsSetPinCodeUseCase
import care.intouch.app.feature.authorization.pinCode.domain.PinCodeRepository
import javax.inject.Inject

class IsSetPinCodeUseCaseImpl @Inject constructor(private val repository: PinCodeRepository) :
    IsSetPinCodeUseCase {
    override suspend fun invoke(): Result<Boolean> = repository.isSetPinCode()
}