package care.intouch.app.account.domain.impl

import care.intouch.app.account.data.dto.AccountState
import care.intouch.app.account.domain.api.AccountStateRepository
import care.intouch.app.account.domain.api.GetAccountStateFlowUC
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAccountStateFlowUCImpl @Inject constructor(private val repository: AccountStateRepository) :
    GetAccountStateFlowUC {
    override suspend fun invoke(): Flow<AccountState?> {
        return repository.getAccountState()
    }
}