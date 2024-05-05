package care.intouch.app.feature.authorization.pinCode.data

import android.content.SharedPreferences
import care.intouch.app.feature.authorization.pinCode.domain.PinCodeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PinCodeRepositoryImpl @Inject constructor(
    private val encryptedPrefs: SharedPreferences
) :
    PinCodeRepository {
    override suspend fun installPinCode(pinCode: String): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                encryptedPrefs.edit().putString(PIN_CODE, pinCode).apply()
                Result.Success(true)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    override suspend fun verifyPinCode(pinCode: String): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                Result.Success(encryptedPrefs.getString(PIN_CODE, null) == pinCode)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    override suspend fun resetPinCode(): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                encryptedPrefs.edit().remove(PIN_CODE).apply()
                Result.Success(true)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    override suspend fun isSetPinCode(): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                if (encryptedPrefs.getString(PIN_CODE, null).isNullOrBlank()) {
                    Result.Success(false)
                } else Result.Success(true)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    companion object {
        private const val PIN_CODE = "pin_code"
    }
}