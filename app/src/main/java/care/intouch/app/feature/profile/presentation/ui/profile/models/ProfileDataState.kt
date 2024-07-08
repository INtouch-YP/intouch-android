package care.intouch.app.feature.profile.presentation.ui.profile.models

import care.intouch.app.R
import care.intouch.uikit.common.StringVO

data class ProfileDataState(
    val dataIsValid: Boolean = false,
    val name: ProfileInformationData = ProfileInformationData(StringVO.Plain(""), false),
    val lastName: ProfileInformationData = ProfileInformationData(StringVO.Plain(""), false),
    val email: ProfileInformationData = ProfileInformationData(StringVO.Plain(""), false),
    val errorMessage: StringVO = StringVO.Resource(R.string.problem_with_connection),
    val successMessage: StringVO = StringVO.Resource(R.string.info_about_change_profile_data)
)
