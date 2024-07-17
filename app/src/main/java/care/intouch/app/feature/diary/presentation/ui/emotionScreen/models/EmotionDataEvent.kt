package care.intouch.app.feature.diary.presentation.ui.emotionScreen.models

sealed class EmotionDataEvent {
    data class OnEmotionDescriptionClicked(val description: EmotionDescriptionTask) :
        EmotionDataEvent()

    data class OnEmotionSwap(val emotionDesc: EmotionDesc) : EmotionDataEvent()
    data object OnSaveButtonClicked : EmotionDataEvent()
}