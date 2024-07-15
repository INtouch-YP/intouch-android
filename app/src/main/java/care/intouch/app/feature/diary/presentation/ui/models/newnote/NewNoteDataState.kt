package care.intouch.app.feature.diary.presentation.ui.models.newnote

import care.intouch.app.feature.diary.presentation.ui.models.Mood

data class NewNoteDataState(
    val id: Int = 0,
    val eventDetails: String = "",
    val thoughtsAnalysis: String = "",
    val emotionType: String = "",
    val physicalSensations: String = "",
    val primaryEmotion: Emotion = Emotion.BLANC,
    val clarifyEmotions: List<Mood> = emptyList(),
    val shareWithDoc: Boolean = false
)
