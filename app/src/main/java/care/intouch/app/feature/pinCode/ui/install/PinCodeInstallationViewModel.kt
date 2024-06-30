package care.intouch.app.feature.pinCode.ui.install

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.pinCode.data.PinCodeRepository
import care.intouch.app.feature.pinCode.ui.IsFullPinCode.IS_FULL_PIN_CODE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinCodeInstallationViewModel @Inject constructor(
    private val repository: PinCodeRepository
) : ViewModel() {

    private val _state =
        MutableStateFlow<PinCodeInstallationScreenState>(PinCodeInstallationScreenState.Default)
    val state: StateFlow<PinCodeInstallationScreenState> = _state.asStateFlow()

    private var enteringPinCode: String = ""

    fun onEvent(event: PinCodeInstallationEvent) {
        when (event) {
            is PinCodeInstallationEvent.Entering -> {
                enteringPinCode = event.pinCode
                if (enteringPinCode.length == IS_FULL_PIN_CODE) {
                    _state.update { PinCodeInstallationScreenState.FullPinCode }
                }
            }

            PinCodeInstallationEvent.Skip -> {
                viewModelScope.launch { repository.skipPinCode() }
            }
        }
    }
}