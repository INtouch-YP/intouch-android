package care.intouch.app.feature.authorization.pinCode.data

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PinCodeRepositoryImpl @Inject constructor(
    private val encryptedPrefs: SharedPreferencesHelper,
    private val defaultPrefs: SharedPreferences,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PinCodeRepository {

    override suspend fun installPinCode(pinCode: String): Result<Boolean> {
        return withContext(ioDispatcher) {
            try {
                encryptedPrefs.sharedPreferences.edit {
                    putString(PIN_CODE, pinCode)
                }
                defaultPrefs.edit {
                    putBoolean(SKIPPED, false)
                }
                Result.success(true)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun verifyPinCode(pinCode: String): Result<Boolean> {
        return withContext(ioDispatcher) {
            try {
                if (encryptedPrefs.sharedPreferences.getString(PIN_CODE, null) == pinCode) {
                    Result.success(true)
                } else Result.success(false)

            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun resetPinCode(): Result<Boolean> {
        return withContext(ioDispatcher) {
            try {
                encryptedPrefs.sharedPreferences.edit {
                    remove(PIN_CODE)
                }
                Result.success(true)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun isPinCodeSet(): Result<Boolean> {
        return withContext(ioDispatcher) {
            try {
                if (encryptedPrefs.sharedPreferences.getString(PIN_CODE, null).isNullOrBlank()) {
                    Result.success(false)
                } else Result.success(true)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun skipPinCode(): Result<Boolean> {
        return withContext(ioDispatcher) {
            try {
                defaultPrefs.edit {
                    putBoolean(SKIPPED, true)
                }
                Result.success(true)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    companion object {
        private const val PIN_CODE = "pin_code"
        private const val SKIPPED = "skipped"
    }
}