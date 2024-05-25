package care.intouch.app.account.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.account.domain.api.ClearAccountUC
import care.intouch.app.account.domain.api.CreateAccountUC
import care.intouch.app.account.domain.api.GetAccountStateFlowUC
import care.intouch.app.account.domain.api.GetAccountStateUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModule @Inject constructor(
    private val clearAccountUC: ClearAccountUC,
    private val createAccountUC: CreateAccountUC,
    private val getAccountStateFlowUC: GetAccountStateFlowUC,
    private val getAccountStateUC: GetAccountStateUC,
) : ViewModel() {
    fun clearAccount() {
        viewModelScope.launch {
            clearAccountUC()
        }
    }

    fun createAccount() {
        viewModelScope.launch {
            createAccountUC(1, "12", "123")
        }
    }

    fun getAccountStateFlow() {
        viewModelScope.launch {
            getAccountStateFlowUC()
        }
    }

    fun getAccountState() {
        viewModelScope.launch {
            getAccountStateUC()
        }
    }
}