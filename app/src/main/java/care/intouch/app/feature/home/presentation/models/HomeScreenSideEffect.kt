package care.intouch.app.feature.home.presentation.models

import care.intouch.uikit.common.StringVO

sealed interface HomeScreenSideEffect {
    data class ShowDialog(
        val title: StringVO = StringVO.Plain(""),
        val massage: StringVO = StringVO.Plain(""),
        val onConfirmButtonText: StringVO = StringVO.Plain(""),
        val onDismissButtonText: StringVO = StringVO.Plain(""),
        val onConfirm: () -> Unit = {},
        val onDismiss: () -> Unit = {},
    ) : HomeScreenSideEffect
}
