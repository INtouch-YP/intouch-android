package care.intouch.app.feature.authorization.presentation.ui.models

import care.intouch.uikit.common.StringVO

data class AuthScreenState(
    val password: String = "",
    val login: String = "",
    val loginCaption: StringVO = StringVO.Plain(""),
    val passwordCaption: StringVO = StringVO.Plain(""),
    val isPasswordVisible: Boolean = false,
    val isIconVisible: Boolean = true,
    val isErrorLogin: Boolean = false,
    val isErrorPassword: Boolean = false,
)