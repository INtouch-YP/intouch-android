package care.intouch.app.feature.diary.presentation.ui.fillingOutScreen.models

import androidx.constraintlayout.compose.Visibility


data class FillingOutScreenState(
    val detailsText: String = "",
    val analysisText: String = "",
    val typeText: String = "",
    val sensationsText: String = "",
    val visible: Boolean = false
)