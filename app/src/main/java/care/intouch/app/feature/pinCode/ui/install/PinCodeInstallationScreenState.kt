package care.intouch.app.feature.pinCode.ui.install

import androidx.compose.runtime.Immutable

@Immutable
interface PinCodeInstallationScreenState {
    @Immutable
    data object Default : PinCodeInstallationScreenState

    @Immutable
    data object FullPinCode : PinCodeInstallationScreenState
}