package care.intouch.app.feature.profile.domain.profile.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed class RedactUserEmailResponse {

    @Serializable
    class RedactUserEmailSuccess : RedactUserEmailResponse()

    @Serializable
    class RedactUserEmailError(
        @SerialName("non_field_errors")
        val message: String
    ): RedactUserEmailResponse()
}