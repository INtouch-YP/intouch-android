package care.intouch.app.feature.diary.presentation.ui.models

import care.intouch.uikit.common.StringVO

data class DialogState(
    val title: StringVO = StringVO.Plain(""),
    val massage: StringVO = StringVO.Plain(""),
    val onConfirmButtonText: StringVO = StringVO.Plain("Confirm"),
    val onDismissButtonText: StringVO = StringVO.Plain("Cancel"),
    val onConfirm: () -> Unit = {},
    val onDismiss: () -> Unit = {}
)