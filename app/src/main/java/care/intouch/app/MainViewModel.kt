package care.intouch.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.authorization.data.dto.AccountState
import care.intouch.app.feature.authorization.domain.api.UserRepository
import care.intouch.app.feature.authorization.domain.api.UserStorage
import care.intouch.app.feature.authorization.domain.useCase.GetAccountStateUC
import care.intouch.app.feature.common.Resource
import care.intouch.app.models.MainActivitySideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAccountStateUC: GetAccountStateUC,
    private val userRepository: UserRepository,
    private val userStorage: UserStorage
) : ViewModel() {

    private val _sideEffect = MutableSharedFlow<MainActivitySideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        viewModelScope.launch {
            val accountState = getAccountStateUC.invoke()
            when (accountState) {
                is AccountState.Account -> {
                    updateUserInfo()
                }

                AccountState.NoAccount -> {
                    navigateToAuth()
                }
            }
        }
    }

    private fun updateUserInfo() {
        viewModelScope.launch {
            when (val userInfo = userRepository.getUser()) {
                is Resource.Success -> {
                    userStorage.save(userInfo.data)
                }

                is Resource.Error -> {
                    showErrorMessageWithAction(
                        message = userInfo.error.message,
                    ) {
                        updateUserInfo()
                    }
                }
            }
        }
    }

    private fun showErrorMessageWithAction(
        message: String,
        onAction: () -> Unit
    ) {
        viewModelScope.launch {
            _sideEffect.emit(
                MainActivitySideEffect.ShowToastWithAction(
                    message = message,
                    onActionClicked = onAction
                )
            )
        }
    }

    private fun navigateToAuth() {
        viewModelScope.launch {
            _sideEffect.emit(
                MainActivitySideEffect.NavigatedToAuth
            )
        }
    }
}