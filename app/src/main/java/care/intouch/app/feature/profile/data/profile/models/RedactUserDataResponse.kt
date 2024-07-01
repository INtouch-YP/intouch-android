package care.intouch.app.feature.profile.data.profile.models

data class RedactUserDataResponse(
    val firstName: String,
    val lastName: String,
    val email: String,
    val dateOfBirth: String,
    val photo: String
)