package care.intouch.app.feature.profile.domain.profile.models

sealed class RedactUserDataResponse {
    data class RedactUserDataSuccess(
        val profileData: ProfileData
    ) : RedactUserDataResponse()

    class RedactUserDataError: RedactUserDataResponse()
}