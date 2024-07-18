package care.intouch.app.feature.profile.presentation.ui.profile.models

import care.intouch.uikit.common.StringVO

data class ProfileState (
    val dataIsValid: Boolean = false,
    val dataIsNotValidMessage: StringVO = StringVO.Plain(""),
    val name: StringVO = StringVO.Plain(""),
    val nameIsValid: Boolean = false,
    val lastName: StringVO = StringVO.Plain(""),
    val lastNameIsValid: Boolean = false,
    val email: StringVO = StringVO.Plain(""),
    val emailIsValid: Boolean = false,
    val resultMessageOfChangeNameRequest: StringVO = StringVO.Plain(""),
    val nameResponseHasBeenReceived: Boolean = false,
    val nameResponseIsSuccess: Boolean = true,
    val resultMessageOfChangeEmailRequest: StringVO = StringVO.Plain(""),
    val emailResponseIsSuccess: Boolean = false,
    val emailColorMessageIsGreenOrRed: Boolean = true,
    val saveChangesButtonVisibility: Boolean = false,
    val nameTextFieldEnabled: Boolean = false,
    val lastNameTextFieldEnabled: Boolean = false,
    val emailTextFieldEnabled: Boolean = false,
    val nameButtonEnabled: Boolean = true,
    val lastNameButtonEnabled: Boolean = true,
    val emailButtonEnabled: Boolean = true,
    val emailChangeDeepLinkRequestSent: Boolean = false,
    val emailChangeDeepLinkRequestSentIsSuccess: Boolean = false,
    val emailChangeDeepLinkRequestMessage: StringVO = StringVO.Plain("")
    )