package care.intouch.app.feature.diary.presentation.ui.models.newnote

import care.intouch.app.feature.diary.presentation.ui.models.Mood

data class NewNoteUIState(
    val primaryEmotion: Emotion = Emotion.BLANC,
    val clarifyEmotions: List<Mood> = emptyList(),
    val isLoading: Boolean = false,
)
