package care.intouch.app.feature.authorization.data.api

import care.intouch.app.feature.authorization.domain.dto.AccountModel
import kotlinx.coroutines.flow.Flow

interface AccountLocalDataSource {
    suspend fun saveAccountInformation(account: AccountModel)
    suspend fun getAccountInformation(): AccountModel?
    fun getAccountFlow(): Flow<AccountModel?>
    suspend fun hasAccount(): Boolean
    suspend fun clearAccountInformation()
}