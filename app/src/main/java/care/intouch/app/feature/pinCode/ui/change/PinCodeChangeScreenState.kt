package care.intouch.app.feature.pinCode.ui.change

import androidx.compose.runtime.Immutable

@Immutable
sealed class PinCodeChangeScreenState {
    var isFullPinCode: Boolean = false

    @Immutable
    data object Default : PinCodeChangeScreenState()

    @Immutable
    data object EnterNewPinCode : PinCodeChangeScreenState()

    @Immutable
    data object ConfirmNewPinCode : PinCodeChangeScreenState()

    @Immutable
    data object Confirmed : PinCodeChangeScreenState()

    @Immutable
    data object IncorrectPinCode : PinCodeChangeScreenState()

    @Immutable
    data object NotMatchPinCode : PinCodeChangeScreenState()

    companion object {
        val Initial: PinCodeChangeScreenState = Default
    }

}