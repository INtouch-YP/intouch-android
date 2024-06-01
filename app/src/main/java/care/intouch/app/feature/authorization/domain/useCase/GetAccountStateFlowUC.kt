package care.intouch.app.feature.authorization.domain.useCase

import care.intouch.app.feature.authorization.data.dto.AccountState
import kotlinx.coroutines.flow.Flow

interface GetAccountStateFlowUC {
    suspend operator fun invoke(): Flow<AccountState>
}