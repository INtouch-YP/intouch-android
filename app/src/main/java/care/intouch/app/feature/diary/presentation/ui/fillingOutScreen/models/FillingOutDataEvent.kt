package care.intouch.app.feature.diary.presentation.ui.fillingOutScreen.models

sealed class FillingOutDataEvent {
    data class OnDetailsTextChanged(val text: String) : FillingOutDataEvent()
    data class OnAnalysisTextChanged(val text: String) : FillingOutDataEvent()
    data class OnTypeTextChanged(val text: String) : FillingOutDataEvent()
    data class OnSensationsTextChanged(val text: String) : FillingOutDataEvent()
    data object OnUpdateStateChanged : FillingOutDataEvent()
    data object OnTrashClicked : FillingOutDataEvent()
    data object OnAddEmotionClicked : FillingOutDataEvent()
    data object OnChangeVisible: FillingOutDataEvent()
    data object OnClickSave: FillingOutDataEvent()
}