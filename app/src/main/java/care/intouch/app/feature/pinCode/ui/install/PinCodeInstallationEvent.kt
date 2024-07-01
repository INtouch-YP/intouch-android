package care.intouch.app.feature.pinCode.ui.install

sealed class PinCodeInstallationEvent {
    class Entering(val pinCode: String) : PinCodeInstallationEvent()
    data object Skip : PinCodeInstallationEvent()
}