package care.intouch.app.feature.pinCode.ui.enter

sealed class PinCodeEnterEvent {
    class Entering(val pinCode: String) : PinCodeEnterEvent()
    data object Enter : PinCodeEnterEvent()
}