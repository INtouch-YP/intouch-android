package care.intouch.app.account.domain.impl

import care.intouch.app.account.domain.api.AccountStateRepository
import care.intouch.app.account.domain.api.CreateAccountUC
import javax.inject.Inject

class CreateAccountUCImpl @Inject constructor(private val repository: AccountStateRepository) :
    CreateAccountUC {
    override suspend fun invoke(userId: Int, accessToken: String, refreshToken: String) {
        repository.createAccount(userId, accessToken, refreshToken)
    }
}