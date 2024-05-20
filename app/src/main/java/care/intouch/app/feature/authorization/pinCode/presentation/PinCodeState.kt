package care.intouch.app.feature.authorization.pinCode.presentation

sealed class PinCodeState {
    data object Skipped : PinCodeState()
    data object Installed : PinCodeState()
    data object AlmostInstalled : PinCodeState()
    data object NotInstalled : PinCodeState()
    data object Confirmed : PinCodeState()
    data object IncorrectPinCode : PinCodeState()
    data object Removed : PinCodeState()
    data class Error(val exception: Exception) : PinCodeState()
}
