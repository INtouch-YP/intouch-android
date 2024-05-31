package care.intouch.app.feature.authorization.domain.api

import care.intouch.app.feature.authorization.data.dto.AccountState
import kotlinx.coroutines.flow.Flow

interface AccountStateRepository {
    suspend fun clearAccount()
    suspend fun createAccount(userId: Int, accessToken: String, refreshToken: String)
    suspend fun getAccountState(): Flow<AccountState>
    suspend fun getAccountInformation(): AccountState
}