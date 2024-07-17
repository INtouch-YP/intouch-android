package care.intouch.app.feature.authorization.presentation.ui.models

import care.intouch.uikit.common.StringVO

sealed interface AuthenticationSideEffect {
    data class ShowToast(
        val message: StringVO
    ) : AuthenticationSideEffect
    data object Login:AuthenticationSideEffect
}