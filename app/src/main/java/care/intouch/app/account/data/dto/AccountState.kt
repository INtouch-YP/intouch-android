package care.intouch.app.account.data.dto

sealed class AccountState {
    data object NoAccount : AccountState()
    class Account(userId: Int, accessToken: String, refreshToken: String) : AccountState()
}
