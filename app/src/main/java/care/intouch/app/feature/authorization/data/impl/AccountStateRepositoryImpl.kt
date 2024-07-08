package care.intouch.app.feature.authorization.data.impl

import care.intouch.app.feature.authorization.data.api.AccountLocalDataSource
import care.intouch.app.feature.authorization.data.dto.AccountState
import care.intouch.app.feature.authorization.domain.api.AccountStateRepository
import care.intouch.app.feature.authorization.domain.dto.AccountModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AccountStateRepositoryImpl @Inject constructor(
    private val localDataSource: AccountLocalDataSource,
) : AccountStateRepository {
    override suspend fun clearAccount() {
        localDataSource.clearAccountInformation()
    }

    override suspend fun createAccount(accessToken: String, refreshToken: String) {
        localDataSource.saveAccountInformation(
            AccountModel(accessToken = accessToken, refreshToken = refreshToken)
        )
    }

    override suspend fun getAccountState(): Flow<AccountState> {
        return localDataSource.getAccountFlow().map {
            if (it == null) {
                AccountState.NoAccount
            } else {
                AccountState.Account(
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
                accessToken = account.accessToken,
                refreshToken = account.refreshToken
            )
        }
    }
}