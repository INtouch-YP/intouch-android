package care.intouch.app.core.utils.mappers

import android.net.Uri
import javax.inject.Inject

class DeepLinksMapper @Inject constructor() {
    fun handleDeepLink(data: Uri?): DeepLinkResultWrapper {

        val resetUrlPattern = "${BASE_URL}${RESET_PASSWORD_ENDPOINT}.*".toRegex()

        return when {
            data.toString().matches(resetUrlPattern) -> DeepLinkResultWrapper.ResetPasswordDeepLink
            else -> DeepLinkResultWrapper.AbsentDeepLink
        }
    }
    companion object {
        const val BASE_URL = "https://app.intouch.care"
        const val RESET_PASSWORD_ENDPOINT = "/reset-password/"
    }
}