package care.intouch.app.models

import care.intouch.uikit.common.StringVO

sealed interface MainActivitySideEffect {
    data class ShowToastWithAction(
        val message: StringVO,
        val actionMessage: StringVO,
        val onActionClicked: () -> Unit
    ) : MainActivitySideEffect

    data object NavigatedToAuth : MainActivitySideEffect
    data object NavigatedToMainScreen : MainActivitySideEffect
}