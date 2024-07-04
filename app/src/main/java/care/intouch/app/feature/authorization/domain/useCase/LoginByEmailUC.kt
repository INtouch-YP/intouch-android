package care.intouch.app.feature.authorization.domain.useCase

import care.intouch.app.feature.authorization.domain.api.AccountStateRepository
import care.intouch.app.feature.authorization.domain.api.AuthenticationRepository
import care.intouch.app.feature.authorization.domain.api.UserRepository
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import javax.inject.Inject

interface LoginByEmailUC {
    suspend operator fun invoke(username: String, password: String): Resource<String, ErrorEntity>

    class Base @Inject constructor(
        private val authenticationRepository: AuthenticationRepository,
        private val accountRepository: AccountStateRepository,
        private val userRepository: UserRepository,
    ) : LoginByEmailUC {
        override suspend fun invoke(
            username: String,
            password: String,
        ): Resource<String, ErrorEntity> {
            return when (
                val result = authenticationRepository.getToken(username, password)) {
                is Resource.Success -> {
                    when (val userInformation = userRepository.getUser()) {
                        is Resource.Success -> {
                            accountRepository.clearAccount()
                            accountRepository.createAccount(
                                userInformation.data.id,
                                result.data.accessToken,
                                result.data.refreshToken
                            )
                        }

                        is Resource.Error -> {
                            return Resource.Error(userInformation.error)
                        }
                    }
                    Resource.Success("Success")
                }

                is Resource.Error -> {
                    Resource.Error(result.error)
                }
            }
        }
    }
}