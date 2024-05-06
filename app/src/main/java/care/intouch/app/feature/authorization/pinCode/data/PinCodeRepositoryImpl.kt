package care.intouch.app.feature.authorization.pinCode.data

import android.content.SharedPreferences
import care.intouch.app.feature.authorization.pinCode.domain.PinCodeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class PinCodeRepositoryImpl @Inject constructor(
    @Named("EncryptedSharedPreferences") private val encryptedPrefs: SharedPreferences,
    @Named("DefaultSharedPreferences") private val defaultPrefs: SharedPreferences
) : PinCodeRepository {

    private var tempPass: String? = null

    override suspend fun installPinCode(pinCode: String): PinCodeState {
        if (tempPass == null) {
            tempPass = pinCode
            return PinCodeState.AlmostInstalled
        } else if (tempPass != pinCode) {
            return PinCodeState.IncorrectPinCode
        } else {
            return withContext(Dispatchers.IO) {
                try {
                    encryptedPrefs.edit().putString(PIN_CODE, pinCode).apply()
                    defaultPrefs.edit().putBoolean(SKIPPED, false).apply()
                    PinCodeState.Installed
                } catch (e: Exception) {
                    PinCodeState.Error(e)
                }
            }
        }
    }

    override suspend fun verifyPinCode(pinCode: String): PinCodeState {
        return withContext(Dispatchers.IO) {
            try {
                if (encryptedPrefs.getString(PIN_CODE, null) == pinCode) {
                    PinCodeState.Confirmed
                } else PinCodeState.IncorrectPinCode

            } catch (e: Exception) {
                PinCodeState.Error(e)
            }
        }
    }

    override suspend fun resetPinCode(): PinCodeState {
        return withContext(Dispatchers.IO) {
            try {
                encryptedPrefs.edit().remove(PIN_CODE).apply()
                PinCodeState.Removed
            } catch (e: Exception) {
                PinCodeState.Error(e)
            }
        }
    }

    override suspend fun isSetPinCode(): PinCodeState {
        return withContext(Dispatchers.IO) {
            try {
                if (defaultPrefs.getBoolean(SKIPPED, false)) {
                    return@withContext PinCodeState.Skipped
                }
                if (encryptedPrefs.getString(PIN_CODE, null).isNullOrBlank()) {
                    PinCodeState.NotInstalled
                } else PinCodeState.Installed
            } catch (e: Exception) {
                PinCodeState.Error(e)
            }
        }
    }

    override suspend fun skipPinCode(): PinCodeState {
        return withContext(Dispatchers.IO) {
            try {
                defaultPrefs.edit().putBoolean(SKIPPED, true).apply()
                PinCodeState.Skipped
            } catch (e: Exception) {
                PinCodeState.Error(e)
            }
        }
    }

    companion object {
        private const val PIN_CODE = "pin_code"
        private const val SKIPPED = "skipped"
    }
}