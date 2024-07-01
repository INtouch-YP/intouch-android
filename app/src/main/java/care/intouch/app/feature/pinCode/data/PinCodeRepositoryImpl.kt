package care.intouch.app.feature.pinCode.data

import android.content.SharedPreferences
import androidx.core.content.edit
import care.intouch.app.feature.authorization.data.models.mappers.AuthenticationExceptionToErrorMapper
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PinCodeRepositoryImpl @Inject constructor(
    private val encryptedPrefs: SharedPreferencesHelper,
    private val defaultPrefs: SharedPreferences,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val exceptionToErrorMapper: AuthenticationExceptionToErrorMapper
) : PinCodeRepository {

    override suspend fun installPinCode(pinCode: String): Resource<Boolean, ErrorEntity> {
        return withContext(ioDispatcher) {
            try {
                encryptedPrefs.sharedPreferences.edit {
                    putString(PIN_CODE, pinCode)
                }
                defaultPrefs.edit {
                    putBoolean(SKIPPED, false)
                }
                Resource.Success(true)
            } catch (e: Exception) {
                val error = exceptionToErrorMapper.handleException(e)
                Resource.Error(error)
            }
        }
    }

    override suspend fun verifyPinCode(pinCode: String): Resource<Boolean, ErrorEntity> {
        return withContext(ioDispatcher) {
            try {
                if (encryptedPrefs.sharedPreferences.getString(PIN_CODE, null) == pinCode) {
                    Resource.Success(true)
                } else Resource.Success(false)

            } catch (e: Exception) {
                val error = exceptionToErrorMapper.handleException(e)
                Resource.Error(error)
            }
        }
    }

    override suspend fun resetPinCode(): Resource<Boolean, ErrorEntity> {
        return withContext(ioDispatcher) {
            try {
                encryptedPrefs.sharedPreferences.edit {
                    remove(PIN_CODE)
                }
                Resource.Success(true)
            } catch (e: Exception) {
                val error = exceptionToErrorMapper.handleException(e)
                Resource.Error(error)
            }
        }
    }

    override suspend fun isPinCodeSet(): Resource<Boolean, ErrorEntity> {
        return withContext(ioDispatcher) {
            try {
                if (encryptedPrefs.sharedPreferences.getString(PIN_CODE, null).isNullOrBlank()) {
                    Resource.Success(false)
                } else Resource.Success(true)
            } catch (e: Exception) {
                val error = exceptionToErrorMapper.handleException(e)
                Resource.Error(error)
            }
        }
    }

    override suspend fun skipPinCode(): Resource<Boolean, ErrorEntity> {
        return withContext(ioDispatcher) {
            try {
                defaultPrefs.edit {
                    putBoolean(SKIPPED, true)
                }
                Resource.Success(true)
            } catch (e: Exception) {
                val error = exceptionToErrorMapper.handleException(e)
                Resource.Error(error)
            }
        }
    }

    companion object {
        private const val PIN_CODE = "pin_code"
        private const val SKIPPED = "skipped"
    }
}