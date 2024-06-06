package care.intouch.app.feature.profile.presentation.ui.profile

data class ResultOfCheckProfileData(
    val dataIsValid: Boolean = false,
    val name: String = "",
    val lastName: String = "",
    val email: String = "",
    val message: String = ""
)
