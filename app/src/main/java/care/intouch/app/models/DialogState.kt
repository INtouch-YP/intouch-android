package care.intouch.app.models

import care.intouch.uikit.common.StringVO

data class DialogState(
    val header: StringVO = StringVO.Plain(""),
    val message: StringVO = StringVO.Plain(""),
    val image: Int = 0,
    val onConfirmButtonText: StringVO = StringVO.Plain("Confirm"),
    val onDismissButtonText: StringVO = StringVO.Plain("Cancel"),
    val onConfirm: () -> Unit = {},
    val onDismiss: () -> Unit = {}
)