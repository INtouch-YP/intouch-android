package care.intouch.app.feature.profile.presentation.ui.profile.models

import care.intouch.app.feature.profile.presentation.ui.profile.models.ProfileInformationData
import care.intouch.uikit.common.StringVO

data class ResultOfCheckProfileData(
    val dataIsValid: Boolean = false,
    val name: ProfileInformationData,
    val lastName: ProfileInformationData,
    val email: ProfileInformationData,
    val errorMessage: StringVO,
    val successMessage: StringVO
)
