package care.intouch.app.feature.authorization.presentation.ui.models

import care.intouch.app.core.utils.BLANC_STRING

data class PasswordRecoveryScreenState(
    val textFieldValue: String = BLANC_STRING,
    val enableButton: Boolean = false,
    val isVisibleCaption: Boolean = false
)
