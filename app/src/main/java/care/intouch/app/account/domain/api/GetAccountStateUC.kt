package care.intouch.app.account.domain.api

import care.intouch.app.account.data.dto.AccountState

interface GetAccountStateUC {
    suspend operator fun invoke(): AccountState
}