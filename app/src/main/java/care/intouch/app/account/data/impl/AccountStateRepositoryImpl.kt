package care.intouch.app.account.data.impl

import care.intouch.app.account.data.api.AccountLocalDataSource
import care.intouch.app.account.data.dto.AccountState
import care.intouch.app.account.domain.api.AccountStateRepository
import care.intouch.app.account.domain.dto.AccountModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AccountStateRepositoryImpl @Inject constructor(
    private val localDataSource: AccountLocalDataSource,
) :
    AccountStateRepository {
    override suspend fun clearAccount() {
        localDataSource.clearAccountInformation()
    }

    override suspend fun createAccount(userId: Int, accessToken: String, refreshToken: String) {
        localDataSource.saveAccountInformation(
            AccountModel(userId = userId, accessToken = accessToken, refreshToken = refreshToken)
        )
    }

    override suspend fun getAccountState(): Flow<AccountState?> {
        return localDataSource.getAccountFlow().map {
            if (it == null) {
                AccountState.NoAccount
            } else {
                AccountState.Account(
                    userId = it.userId,
                    accessToken = it.accessToken,
                    refreshToken = it.refreshToken
                )
            }
        }
    }

    override suspend fun getAccountInformation(): AccountState {
        val account = localDataSource.getAccountInformation()
        return if (account == null) {
            AccountState.NoAccount
        } else {
            AccountState.Account(
                userId = account.userId,
                accessToken = account.accessToken,
                refreshToken = account.refreshToken
            )
        }
    }
}