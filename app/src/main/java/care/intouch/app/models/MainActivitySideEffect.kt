package care.intouch.app.models

sealed interface MainActivitySideEffect {
    data class ShowToastWithAction(
        val message: String,
        val actionMessage: String = "",
        val onActionClicked: () -> Unit) : MainActivitySideEffect

    data object NavigatedToAuth : MainActivitySideEffect
}