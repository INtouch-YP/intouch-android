package care.intouch.app.feature.pinCode.ui.confirm

import androidx.lifecycle.SavedStateHandle
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
class PinCodeConfirmationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: PinCodeRepository
) : ViewModel() {

    private val pinCodeInst: String = savedStateHandle["pinCodeInst"] ?: ""


    init {
        onEvent(PinCodeConfirmationEvent.Init(pinCodeInst))
    }

    private val _state = MutableStateFlow(PinCodeConfirmationScreenState.Initial)
    val state: StateFlow<PinCodeConfirmationScreenState> = _state.asStateFlow()

    private var tempPinCode: String? = null
    private var enteringPinCode: String = ""

    fun onEvent(event: PinCodeConfirmationEvent) {
        when (event) {
            is PinCodeConfirmationEvent.Init -> {
                if (event.pinCode.isNullOrEmpty()) {
                    _state.update { PinCodeConfirmationScreenState.NotConfirmed }
                }
                tempPinCode = event.pinCode
            }

            is PinCodeConfirmationEvent.Statement -> {
                if (tempPinCode == event.pinCode) {
                    viewModelScope.launch {
                        when (repository.installPinCode(pinCode = event.pinCode)) {
                            is Resource.Error -> _state.update { PinCodeConfirmationScreenState.NotConfirmed }
                            is Resource.Success -> _state.update { PinCodeConfirmationScreenState.Confirmed }
                        }
                    }
                } else {
                    enteringPinCode = ""
                    _state.update { PinCodeConfirmationScreenState.NotConfirmed }
                }
            }

            PinCodeConfirmationEvent.Skip -> {
                viewModelScope.launch {
                    repository.skipPinCode()
                }
            }

            is PinCodeConfirmationEvent.Entering -> {
                enteringPinCode = event.pinCode
                if (enteringPinCode.length == IS_FULL_PIN_CODE){
                    _state.update {
                        PinCodeConfirmationScreenState.Default.apply {
                            isFullPinCode = true
                        }
                    }
                }
            }
        }
    }
}