package care.intouch.app.feature.authorization.pinCode.data

import android.content.SharedPreferences
import care.intouch.app.feature.authorization.pinCode.domain.PinCodeRepository
import javax.inject.Inject

class PinCodeRepositoryImpl @Inject constructor(private val encryptedPrefs: SharedPreferences) :
    PinCodeRepository {
    override fun installPinCode(pinCode: String): Result<Boolean>  {
        return try {
            resetPinCode()
            encryptedPrefs.edit().putString(PIN_CODE, pinCode).apply()
            Result.Success(true)
        } catch (e: Exception){
            Result.Error(IllegalArgumentException(e))
        }
    }

    override fun verifyPinCode(pinCode: String): Result<Boolean>  {
        return try {
            Result.Success(encryptedPrefs.getString(PIN_CODE, null) == pinCode)
        } catch (e: Exception){
            Result.Error(IllegalArgumentException(e))
        }
    }

    override fun resetPinCode(): Result<Boolean>  {
        encryptedPrefs.edit().remove(PIN_CODE).apply()
        return Result.Success(true)
    }

    companion object {
        private const val ENCRYPT = "encrypt_shared_prefs"
        private const val PIN_CODE = "pin_code"
    }
}