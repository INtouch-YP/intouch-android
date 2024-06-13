package care.intouch.app.feature.profile.presentation.ui.profile.models

class ViewsComponentsState(
    val saveChangesButtonVisibility: Boolean = false,
    val informationIsUpdate: Boolean = false,
    val nameTextFieldEnabled: Boolean = false,
    val lastNameTextFieldEnabled: Boolean = false,
    val emailTextFieldEnabled: Boolean = false,
    val nameButtonEnabled: Boolean = true,
    val lastNameButtonEnabled: Boolean = true,
    val emailButtonEnabled: Boolean = true
)