package care.intouch.app.feature.profile.domain.profile.models

sealed class UpdateUserDataResponse {
    data class UpdateUserDataSuccess(
        val profileData: ProfileData
    ) : UpdateUserDataResponse()

    data class UpdateUserDataError(
        val details: String = "No message"
    ): UpdateUserDataResponse()
}