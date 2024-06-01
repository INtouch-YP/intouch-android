package care.intouch.app.feature.authorization.data.dto

sealed class AccountState {
    data object NoAccount : AccountState()
    data class Account(val userId: Int, val accessToken: String, val refreshToken: String) :
        AccountState()
}