package care.intouch.app.feature.authorization.domain.impl

import care.intouch.app.feature.authorization.domain.api.AccountStateRepository
import care.intouch.app.feature.authorization.domain.useCase.ClearAccountUC
import javax.inject.Inject

class ClearAccountUCImpl @Inject constructor(private val repository: AccountStateRepository) :
    ClearAccountUC {
    override suspend fun invoke() {
        repository.clearAccount()
    }
}