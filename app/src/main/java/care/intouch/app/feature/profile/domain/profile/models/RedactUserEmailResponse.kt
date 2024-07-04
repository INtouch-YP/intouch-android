package care.intouch.app.feature.profile.domain.profile.models

sealed class RedactUserEmailResponse {

    class RedactUserEmailSuccess : RedactUserEmailResponse()

    class RedactUserEmailError(
        val message: String
    ): RedactUserEmailResponse()
}