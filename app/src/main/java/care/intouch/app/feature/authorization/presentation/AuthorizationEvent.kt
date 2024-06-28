package care.intouch.app.feature.authorization.presentation

sealed class AuthorizationEvent {
    data class OnGetUserInfo(
        val userId: String?,
        val token: String?
    ): AuthorizationEvent()

    data class OnSetPassword(
        val password: String,
        val confirmPassword: String,
    ): AuthorizationEvent()
}