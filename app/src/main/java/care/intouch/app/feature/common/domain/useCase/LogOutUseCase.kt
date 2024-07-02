package care.intouch.app.feature.common.domain.useCase

import care.intouch.app.feature.authorization.domain.api.AccountStateRepository
import care.intouch.app.feature.authorization.domain.api.UserStorage
import javax.inject.Inject

interface LogOutUseCase {
    suspend operator fun invoke()

    class Base @Inject constructor(
        private val userStorage: UserStorage,
        private val accountRepository: AccountStateRepository
    ) : LogOutUseCase {
        override suspend fun invoke() {
            userStorage.clear()
            accountRepository.clearAccount()
        }
    }
}