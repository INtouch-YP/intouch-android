package care.intouch.app.feature.authorization.domain.impl

import care.intouch.app.feature.authorization.domain.api.AccountStateRepository
import care.intouch.app.feature.authorization.domain.useCase.CreateAccountUC
import javax.inject.Inject

class CreateAccountUCImpl @Inject constructor(private val repository: AccountStateRepository) :
    CreateAccountUC {
    override suspend fun invoke(userId: Int, accessToken: String, refreshToken: String) {
        repository.createAccount(userId, accessToken, refreshToken)
    }
}