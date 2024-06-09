package care.intouch.app.feature.profile.presentation.ui.profile

data class ResultOfCheckProfileData(
    val dataIsValid: Boolean = false,
    val name: ProfileInformationData,
    val lastName: ProfileInformationData,
    val email: ProfileInformationData,
    val errorMessage: String = "",
    val successMessage: String = "Information successfully updated"
)
