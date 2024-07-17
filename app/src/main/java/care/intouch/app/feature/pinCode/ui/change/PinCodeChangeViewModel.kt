package care.intouch.app.feature.pinCode.ui.change

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
class PinCodeChangeViewModel @Inject constructor(
    private val repository: PinCodeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PinCodeChangeScreenState.Initial)
    val state: StateFlow<PinCodeChangeScreenState> = _state.asStateFlow()

    private var tempPinCode: String? = null
    private var enteringPinCode: String = ""

    fun onEvent(event: PinCodeChangeEvent) {
        when (event) {
            is PinCodeChangeEvent.Statement -> {

                when (_state.value) {
                    PinCodeChangeScreenState.EnterNewPinCode -> {
                        tempPinCode = event.pinCode
                        _state.update {
                            _state.value.isFullPinCode = false
                            PinCodeChangeScreenState.ConfirmNewPinCode
                        }
                    }

                    PinCodeChangeScreenState.Default -> {
                        verifyOldPinCode(event.pinCode)
                    }

                    PinCodeChangeScreenState.IncorrectPinCode -> {
                        verifyOldPinCode(event.pinCode)
                    }

                    else -> {
                        if (tempPinCode == event.pinCode) {
                            viewModelScope.launch {
                                when (val result =
                                    repository.installPinCode(pinCode = event.pinCode)) {
                                    is Resource.Error -> _state.update {
                                        _state.value.isFullPinCode = false
                                        PinCodeChangeScreenState.NotMatchPinCode
                                    }

                                    is Resource.Success -> {
                                        if (result.data) {
                                            _state.update {
                                                _state.value.isFullPinCode = false
                                                PinCodeChangeScreenState.Confirmed
                                            }
                                        } else {
                                            _state.update {
                                                _state.value.isFullPinCode = false
                                                PinCodeChangeScreenState.NotMatchPinCode
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            enteringPinCode = ""
                            _state.update {
                                _state.value.isFullPinCode = false
                                PinCodeChangeScreenState.NotMatchPinCode
                            }
                        }
                    }
                }
            }

            is PinCodeChangeEvent.Entering -> {
                enteringPinCode = event.pinCode
                if (enteringPinCode.length == IS_FULL_PIN_CODE) {
                    _state.update {
                        _state.value.isFullPinCode = true
                        state.value
                    }
                }
            }
        }
    }

    private fun verifyOldPinCode(pinCode: String) {
        viewModelScope.launch {
            when (val result = repository.verifyPinCode(pinCode)) {
                is Resource.Error -> _state.update {
                    PinCodeChangeScreenState.IncorrectPinCode
                }

                is Resource.Success -> {
                    if (result.data) {
                        _state.update {
                            PinCodeChangeScreenState.EnterNewPinCode
                        }
                        enteringPinCode = ""
                    } else {
                        _state.update { PinCodeChangeScreenState.IncorrectPinCode }
                    }
                }
            }
        }
    }
}