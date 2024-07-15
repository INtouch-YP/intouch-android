package care.intouch.app.feature.pinCode.ui.change

sealed class PinCodeChangeEvent {

    class Statement(val pinCode: String) : PinCodeChangeEvent()
    class Entering(val pinCode: String) : PinCodeChangeEvent()
}