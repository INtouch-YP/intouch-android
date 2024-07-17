package care.intouch.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.authorization.data.dto.AccountState
import care.intouch.app.feature.authorization.domain.useCase.GetAccountStateFlowUC
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.useCase.LogOutUseCase
import care.intouch.app.feature.common.domain.useCase.UpdateUserProfileCacheUseCase
import care.intouch.app.models.MainActivitySideEffect
import care.intouch.uikit.common.StringVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAccountStateFlowUseCase: GetAccountStateFlowUC,
    private val updateUserProfileCacheUseCase: UpdateUserProfileCacheUseCase,
    private val logOutUseCase: LogOutUseCase
) : ViewModel() {

    var isLoading: Boolean = true

    private val _sideEffect = MutableSharedFlow<MainActivitySideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        viewModelScope.launch {
            getAccountStateFlowUseCase()
                .collect { accountState ->
                    when (accountState) {
                        is AccountState.Account -> {
                            isLoading = false
                            updateUserInfo()
                        }

                        is AccountState.NoAccount -> {
                            isLoading = false
                            logOutUseCase()
                            navigateToAuth()
                        }
                    }
                }
        }
    }

    private fun updateUserInfo() {
        viewModelScope.launch {
            when (val result = updateUserProfileCacheUseCase()) {
                is Resource.Error -> {
                    showErrorMessageWithAction(result.error.message) {
                        updateUserInfo()
                    }
                }

                is Resource.Success -> {
                    navigateToMainScreen()
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
                    message = StringVO.Plain(message),
                    actionMessage = StringVO.Resource(R.string.retry_button),
                    onActionClicked = onAction
                )
            )
        }
    }

    private fun navigateToAuth() {
        isLoading = false
        viewModelScope.launch {
            _sideEffect.emit(
                MainActivitySideEffect.NavigatedToAuth
            )
        }
    }

    private fun navigateToMainScreen() {
        isLoading = false
        viewModelScope.launch {
            _sideEffect.emit(
                MainActivitySideEffect.NavigatedToMainScreen
            )
        }
    }
}