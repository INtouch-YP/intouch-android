package care.intouch.app.feature.authorization.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.authorization.domain.useCase.LoginByEmailUC
import care.intouch.app.feature.authorization.presentation.ui.models.AuthScreenState
import care.intouch.app.feature.authorization.presentation.ui.models.AuthenticationDataEvent
import care.intouch.app.feature.authorization.presentation.ui.models.AuthenticationSideEffect
import care.intouch.app.feature.common.Resource
import care.intouch.app.ui.uiKitSamples.samples.BLANC_STRING
import care.intouch.uikit.common.StringVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModule @Inject constructor(
    private val loginByEmailUC: LoginByEmailUC
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        AuthScreenState(
            password = "",
            login = "",
            loginCaption = StringVO.Plain(""),
            passwordCaption = StringVO.Plain(""),
            isPasswordVisible = false,
            isIconVisible = true,
            isErrorLogin = false,
            isErrorPassword = false
        )
    )
    val uiState = _uiState.asStateFlow()
    private val _sideEffect = MutableSharedFlow<AuthenticationSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    fun onEvent(event: AuthenticationDataEvent) {
        when (event) {
            is AuthenticationDataEvent.OnLoginButtonClicked -> {
                loginByEmail(_uiState.value.login, _uiState.value.password)
            }

            AuthenticationDataEvent.OnLoginErrorChanged -> {
                loginErrorState()
            }

            is AuthenticationDataEvent.OnLoginTextChanged -> {
                saveLogin(event.text)
                loginErrorState()
                loginValidation()
            }

            AuthenticationDataEvent.OnLoginValidationChecked -> {
                loginValidation()
            }

            AuthenticationDataEvent.OnPasswordErrorChanged -> {
                passwordErrorState()
            }

            AuthenticationDataEvent.OnPasswordIconClick -> {
                onPasswordIconClick()
            }

            is AuthenticationDataEvent.OnPasswordTextChanged -> {
                savePassword(event.text)
                passwordErrorState()
                passwordValidation()
            }

            AuthenticationDataEvent.OnPasswordValidationChecked -> {
                passwordValidation()
            }
        }
    }

    private fun savePassword(password: String) {
        _uiState.update { state ->
            state.copy(
                password = password
            )
        }
    }

    private fun onPasswordIconClick() {
        _uiState.update { state ->
            state.copy(
                isPasswordVisible = !uiState.value.isPasswordVisible
            )
        }
    }

    private fun saveLogin(login: String) {
        _uiState.update { state ->
            state.copy(
                login = login
            )
        }
    }

    private fun loginByEmail(username: String, password: String) {
        viewModelScope.launch {
           when (val result = loginByEmailUC.invoke(username, password)) {
                is Resource.Error -> {
                    val errorMessage = result.error.message
                    _sideEffect.emit(AuthenticationSideEffect.ShowToast(StringVO.Plain(errorMessage)))
                }
                is Resource.Success -> {
                    _sideEffect.emit(AuthenticationSideEffect.Login)
                }
            }
        }
    }

    private fun passwordErrorState() {
        _uiState.update { state ->
            state.copy(
                isErrorPassword = !isValidPassword(uiState.value.password)
            )
        }
    }

    private fun loginErrorState() {
        _uiState.update { state ->
            state.copy(
                isErrorLogin = !isValidEmail(uiState.value.login)
            )
        }
    }

    private fun passwordValidation() {
        if (!isValidPassword(uiState.value.password)) {
            _uiState.update { state ->
                state.copy(
                    passwordCaption = StringVO.Resource(care.intouch.app.R.string.incorrect_please_try_again)
                )
            }
        } else {
            _uiState.update { state ->
                state.copy(
                    passwordCaption = StringVO.Plain(BLANC_STRING)
                )
            }
        }
    }

    private fun loginValidation() {
        if (!isValidEmail(uiState.value.login)) {
            _uiState.update { state ->
                state.copy(
                    loginCaption = StringVO.Resource(care.intouch.app.R.string.not_a_valid_e_mail_address)
                )
            }
        } else {
            _uiState.update { state ->
                state.copy(
                    loginCaption = StringVO.Plain(BLANC_STRING)
                )
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return if (email.isBlank()) {
            true
        } else {
            email.matches(emailRegex)
        }
    }

    private fun isValidPassword(password: String): Boolean {
        if (password.isBlank()) return true
        if (password.length < 8) return false
        if (password.firstOrNull { it.isDigit() } == null) return false
        if (password.filter { it.isLetter() }.firstOrNull { it.isUpperCase() } == null) return false
        if (password.filter { it.isLetter() }.firstOrNull { it.isLowerCase() } == null) return false

        return true
    }
}

val emailRegex = Regex(
    "[a-zA-Z0-9\\.\\_\\-]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{1,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{1,25}" +
            ")+"
)