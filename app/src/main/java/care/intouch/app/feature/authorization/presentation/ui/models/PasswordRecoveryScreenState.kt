package care.intouch.app.feature.authorization.presentation.ui.models

import care.intouch.app.core.utils.BLANC_STRING

data class PasswordRecoveryScreenState(
    val errorMessage: String = BLANC_STRING,
    val isSuccess: Boolean = false
)
