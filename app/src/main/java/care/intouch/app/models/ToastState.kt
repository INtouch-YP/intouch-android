package care.intouch.app.models

import care.intouch.uikit.common.StringVO

data class ToastState(
    val massage: StringVO = StringVO.Plain(""),
    val onDismiss: () -> Unit = {}
)
