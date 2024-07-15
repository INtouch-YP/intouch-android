package care.intouch.app.feature.authorization.domain.useCase

import care.intouch.app.feature.authorization.domain.api.PasswordRepository
import care.intouch.app.feature.authorization.domain.dto.NewPasswordModel
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import javax.inject.Inject

interface SetPasswordUseCase {
    suspend operator fun invoke(password: String, confirmPassword: String): Resource<Unit, ErrorEntity>
    class Base @Inject constructor(
        private val passwordRepository: PasswordRepository,
    ) : SetPasswordUseCase {
        override suspend fun invoke(
            password: String,
            confirmPassword: String
        ): Resource<Unit, ErrorEntity> {
            val result = passwordRepository.setPassword(
                NewPasswordModel(
                    password, confirmPassword
                )
            )
            return when (result) {
                is Resource.Success -> {
                    Resource.Success(result.data)
                }

                is Resource.Error -> {
                    Resource.Error(result.error)
                }
            }
        }
    }
}