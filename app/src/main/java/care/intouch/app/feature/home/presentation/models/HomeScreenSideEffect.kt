package care.intouch.app.feature.home.presentation.models

import care.intouch.uikit.common.StringVO

sealed interface HomeScreenSideEffect {
    data class ShowDialog(
        val header: StringVO = StringVO.Plain(""),
        val message: StringVO = StringVO.Plain(""),
        val image: Int = 0,
        val onConfirmButtonText: StringVO = StringVO.Plain(""),
        val onDismissButtonText: StringVO = StringVO.Plain(""),
        val onConfirm: () -> Unit = {},
        val onDismiss: () -> Unit = {},
    ) : HomeScreenSideEffect

    data class ShowToast(
        val message: StringVO = StringVO.Plain(""),
        val onDismiss: () -> Unit = {}
    ) : HomeScreenSideEffect

}
