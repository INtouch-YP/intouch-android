package care.intouch.app.feature.profile.presentation.ui.profile.models

import care.intouch.app.R
import care.intouch.uikit.common.StringVO

data class ProfileState (
    val dataIsValid: Boolean = false,
    val name: StringVO = StringVO.Plain(""),
    val nameIsValid: Boolean = false,
    val lastName: StringVO = StringVO.Plain(""),
    val lastNameIsValid: Boolean = false,
    val email: StringVO = StringVO.Plain(""),
    val emailIsValid: Boolean = false,
    val errorMessage: StringVO = StringVO.Resource(R.string.problem_with_connection),
    val successMessage: StringVO = StringVO.Resource(R.string.info_about_change_profile_data),
    val saveChangesButtonVisibility: Boolean = false,
    val informationIsUpdate: Boolean = false,
    val nameTextFieldEnabled: Boolean = false,
    val lastNameTextFieldEnabled: Boolean = false,
    val emailTextFieldEnabled: Boolean = false,
    val nameButtonEnabled: Boolean = true,
    val lastNameButtonEnabled: Boolean = true,
    val emailButtonEnabled: Boolean = true,
    val colorOfMessageIsGreenOrRed: Boolean = true
)