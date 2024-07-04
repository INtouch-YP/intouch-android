package care.intouch.app.feature.pinCode.ui.enter

import androidx.compose.runtime.Immutable

@Immutable
sealed interface PinCodeEnterState {
    @Immutable
    data object Default : PinCodeEnterState

    @Immutable
    data object Confirmed : PinCodeEnterState

    @Immutable
    data object NotConfirmed : PinCodeEnterState

    @Immutable
    data object FullPinCode : PinCodeEnterState

    companion object {
        val Initial: PinCodeEnterState = Default
    }
}