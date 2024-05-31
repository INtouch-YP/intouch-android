package care.intouch.app.feature.authorization.data.impl

import android.content.SharedPreferences
import androidx.core.content.edit
import care.intouch.app.feature.authorization.data.api.AccountLocalDataSource
import care.intouch.app.feature.authorization.domain.dto.AccountModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class AccountLocalDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val coroutineScope: CoroutineScope,
    private val json: Json,
) : AccountLocalDataSource, SharedPreferences.OnSharedPreferenceChangeListener {

    private val _accountUpdateFlow = MutableStateFlow<AccountModel?>(null)

    init {
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
        coroutineScope.launch {
            _accountUpdateFlow.value = getAccountInformation()
        }
    }

    override suspend fun saveAccountInformation(account: AccountModel) {
        withContext(Dispatchers.IO) {
            val data = json.encodeToString(account)
            sharedPreferences.edit { putString(KEY, data) }
        }
    }

    override suspend fun getAccountInformation(): AccountModel? {
        return withContext(Dispatchers.IO) {
            sharedPreferences.getString(KEY, null)?.let {
                json.decodeFromString<AccountModel>(it)
            }
        }
    }

    override fun getAccountFlow(): Flow<AccountModel?> {
        return _accountUpdateFlow
    }

    override suspend fun hasAccount(): Boolean {
        return withContext(Dispatchers.IO) {
            sharedPreferences.contains(KEY)
        }
    }

    override suspend fun clearAccountInformation() {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit { remove(KEY) }
            _accountUpdateFlow.emit(null)
        }
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
        if (p1 == KEY) {
            coroutineScope.launch {
                _accountUpdateFlow.update {
                    getAccountInformation()
                }
            }
        }
    }

    companion object {
        const val KEY = "KEY"
    }
}