package care.intouch.app.feature.profile.presentation.ui.profile.models

import care.intouch.app.R
import care.intouch.uikit.common.StringVO

sealed class ProfileDataEvent {

    data class OnName(
        val name:String,
        val errorLength: StringVO = StringVO.Resource(resId = R.string.profile_small_name_error),
        val errorInvalidChar: StringVO = StringVO.Resource(resId = R.string.profile_invalid_char_error)
    ): ProfileDataEvent()

    data class OnLastName(
        val lastName:String,
        val errorLength: StringVO = StringVO.Resource(resId = R.string.profile_small_last_name_error),
        val errorInvalidChar: StringVO = StringVO.Resource(resId = R.string.profile_invalid_char_error)
    ): ProfileDataEvent()

    data class OnEmail(
        val email:String,
        val errorEmailNotValid: StringVO = StringVO.Resource(resId = R.string.email_not_valid_error)
    ): ProfileDataEvent()

    data class OnEditNameButtonClick(
        val name: Boolean = true,
        val lastName: Boolean = false,
        val email: Boolean = false,
        val saveChangesButton: Boolean = true,
        val infIsUpdate: Boolean = false
    ): ProfileDataEvent()

    data class OnEditLastNameButtonClick(
        val name: Boolean = false,
        val lastName: Boolean = true,
        val email: Boolean = false,
        val saveChangesButton: Boolean = true,
        val infIsUpdate: Boolean = false
    ): ProfileDataEvent()

    data class OnEditEmailButtonClick(
        val name: Boolean = false,
        val lastName: Boolean = false,
        val email: Boolean = true,
        val saveChangesButton: Boolean = true,
        val infIsUpdate: Boolean = false
    ): ProfileDataEvent()

    data class OnSaveChangesButtonClick(
        val name: Boolean = false,
        val lastName: Boolean = false,
        val email: Boolean = false,
        val saveChangesButton: Boolean = false,
    ): ProfileDataEvent()

    class OnSingOutButtonClick(): ProfileDataEvent()
}
