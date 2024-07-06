package care.intouch.app.feature.profile.data.profile.api

import android.util.Log
import care.intouch.app.feature.profile.domain.profile.models.RedactUserEmailResponse
import care.intouch.app.feature.profile.domain.profile.useCase.RedactUserEmailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RedactUserEmailRepositoryImpl @Inject constructor(
    private val redactUserEmailApi: RedactUserEmailApi
): RedactUserEmailRepository {
    var response: String = ""

    override suspend fun redactUserEmail(newEmail: String): RedactUserEmailResponse {
        return withContext(Dispatchers.IO) {
            try {
                response = redactUserEmailApi.updateUserEmail(newEmail)
                Log.d("INTOUCH_MY_TAG", "doChangeEmail in try")
                RedactUserEmailResponse.RedactUserEmailSuccess()
            } catch (e: Exception) {
                Log.d("INTOUCH_MY_TAG", "doChangeEmail in Exception")
                RedactUserEmailResponse.RedactUserEmailError(response)
            }
        }
    }

}