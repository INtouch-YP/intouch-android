package care.intouch.app.feature.profile.domain.profile.models

import kotlinx.serialization.Serializable

sealed class RedactUserEmailResponse {

    @Serializable
    class RedactUserEmailSuccess : RedactUserEmailResponse()

    @Serializable
    class RedactUserEmailError(
        val message: String?
    ): RedactUserEmailResponse()
}