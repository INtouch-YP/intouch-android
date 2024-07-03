package care.intouch.app.feature.authorization.presentation.ui.models

import care.intouch.uikit.common.StringVO

sealed interface PasswordRecoverySideEffect {
    data object NavigateToPasswordSendInformation: PasswordRecoverySideEffect
    data class ShowToast(val message: StringVO): PasswordRecoverySideEffect
}