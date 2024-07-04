package care.intouch.app.feature.authorization.presentation

sealed class AuthorizationEvent {
    data class OnGetUserInfo(
        val userId: String?,
        val token: String?
    ): AuthorizationEvent()

    data class OnSavePassword(
        val password: String,
        val confirmPassword: String,
    ): AuthorizationEvent()

    data class OnSetPassword(
        val password: String
    ): AuthorizationEvent()

    data class OnSetConfirmPassword(
        val confirmPassword: String
    ): AuthorizationEvent()

    data class OnUpdateAgreementToTerm(
        val isEnable: Boolean
    ): AuthorizationEvent()

    data object OnCreatePinCodeButtonClick : AuthorizationEvent()
}