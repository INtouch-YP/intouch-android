package care.intouch.app.feature.pinCode.ui.confirm

sealed class PinCodeConfirmationEvent {
    class Init(val pinCode: String?) : PinCodeConfirmationEvent()
    class Statement(val pinCode: String) : PinCodeConfirmationEvent()
    class Entering(val pinCode: String): PinCodeConfirmationEvent()
    data object Skip: PinCodeConfirmationEvent()
}