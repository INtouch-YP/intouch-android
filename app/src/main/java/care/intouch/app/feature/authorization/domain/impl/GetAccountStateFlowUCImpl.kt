package care.intouch.app.feature.authorization.domain.impl

import care.intouch.app.feature.authorization.data.dto.AccountState
import care.intouch.app.feature.authorization.domain.api.AccountStateRepository
import care.intouch.app.feature.authorization.domain.useCase.GetAccountStateFlowUC
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAccountStateFlowUCImpl @Inject constructor(private val repository: AccountStateRepository) :
    GetAccountStateFlowUC {
    override suspend fun invoke(): Flow<AccountState> {
        return repository.getAccountState()
    }
}