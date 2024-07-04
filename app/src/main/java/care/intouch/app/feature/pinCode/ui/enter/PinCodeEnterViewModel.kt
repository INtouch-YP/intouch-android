package care.intouch.app.feature.pinCode.ui.enter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.common.Resource
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
class PinCodeEnterViewModel @Inject constructor(
    private val repository: PinCodeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PinCodeEnterState.Initial)
    val state: StateFlow<PinCodeEnterState> = _state.asStateFlow()

    private var enteringPinCode: String = ""

    fun onEvent(event: PinCodeEnterEvent) {
        when (event) {
            is PinCodeEnterEvent.Entering -> {
                enteringPinCode = event.pinCode
                if (enteringPinCode.length == IS_FULL_PIN_CODE) {
                    _state.update { PinCodeEnterState.FullPinCode }
                } else _state.update { PinCodeEnterState.Default }
            }

            PinCodeEnterEvent.Enter -> {
                viewModelScope.launch {
                    when (val isConfirmed = repository.verifyPinCode(enteringPinCode)) {
                        is Resource.Error -> _state.update {
                            PinCodeEnterState.NotConfirmed
                        }

                        is Resource.Success -> {
                            if (isConfirmed.data) {
                                _state.update {
                                    PinCodeEnterState.Confirmed
                                }
                            } else {
                                _state.update {
                                    PinCodeEnterState.NotConfirmed
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}