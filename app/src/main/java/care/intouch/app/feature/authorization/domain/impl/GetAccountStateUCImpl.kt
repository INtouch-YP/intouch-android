package care.intouch.app.feature.authorization.domain.impl

import care.intouch.app.feature.authorization.data.dto.AccountState
import care.intouch.app.feature.authorization.domain.api.AccountStateRepository
import care.intouch.app.feature.authorization.domain.useCase.GetAccountStateUC
import javax.inject.Inject

class GetAccountStateUCImpl @Inject constructor(private val repository: AccountStateRepository) :
    GetAccountStateUC {
    override suspend fun invoke(): AccountState {
        return repository.getAccountInformation()
    }
}