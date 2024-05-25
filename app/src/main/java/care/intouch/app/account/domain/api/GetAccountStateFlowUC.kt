package care.intouch.app.account.domain.api

import care.intouch.app.account.data.dto.AccountState
import kotlinx.coroutines.flow.Flow

interface GetAccountStateFlowUC {
    suspend operator fun invoke(): Flow<AccountState?>
}