package care.intouch.app.feature.pinCode.ui.confirm

import androidx.compose.runtime.Immutable

@Immutable
sealed interface PinCodeConfirmationScreenState {

    @Immutable
    data object Default : PinCodeConfirmationScreenState{
        var isFullPinCode = false
    }

    @Immutable
    data object Confirmed : PinCodeConfirmationScreenState

    @Immutable
    data object NotConfirmed : PinCodeConfirmationScreenState

    companion object {
        val Initial: PinCodeConfirmationScreenState = Default
    }
}