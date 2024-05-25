package care.intouch.app.account.domain.impl

import care.intouch.app.account.domain.api.AccountStateRepository
import care.intouch.app.account.domain.api.ClearAccountUC
import javax.inject.Inject

class ClearAccountUCImpl @Inject constructor(private val repository: AccountStateRepository) :
    ClearAccountUC {
    override suspend fun invoke() {
        repository.clearAccount()
    }
}