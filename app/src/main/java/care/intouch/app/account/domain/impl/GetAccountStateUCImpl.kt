package care.intouch.app.account.domain.impl

import care.intouch.app.account.data.dto.AccountState
import care.intouch.app.account.domain.api.AccountStateRepository
import care.intouch.app.account.domain.api.GetAccountStateUC
import javax.inject.Inject

class GetAccountStateUCImpl @Inject constructor(private val repository: AccountStateRepository) :
    GetAccountStateUC {
    override suspend fun invoke(): AccountState {
        return repository.getAccountInformation()
    }
}