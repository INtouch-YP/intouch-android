package care.intouch.app.feature.authorization.domain.useCase

import care.intouch.app.feature.authorization.data.dto.AccountState

interface GetAccountStateUC {
    suspend operator fun invoke(): AccountState
}