package care.intouch.app.feature.diary.presentation.ui.models

import care.intouch.uikit.common.StringVO

sealed interface DiaryPopUp {
    data class ShowPopUp(
        val title: StringVO = StringVO.Plain(""),
        val massage: StringVO = StringVO.Plain(""),
        val onConfirmButtonText: StringVO = StringVO.Plain(""),
        val onDismissButtonText: StringVO = StringVO.Plain(""),
        val onConfirm: () -> Unit = {},
        val onDismiss: () -> Unit = {},
    ) : DiaryPopUp
}