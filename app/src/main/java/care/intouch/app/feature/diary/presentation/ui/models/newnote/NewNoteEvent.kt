package care.intouch.app.feature.diary.presentation.ui.models.newnote

import care.intouch.app.feature.diary.presentation.ui.models.Mood

sealed interface NewNoteEvent {
    data class EventDetailsTextChange(val text: String) : NewNoteEvent
    data class ThoughtsAnalysisTextChange(val text: String) : NewNoteEvent
    data class EmotionalTypeTextChange(val text: String) : NewNoteEvent
    data class PhysicalSensationsTextChange(val text: String) : NewNoteEvent
    data class OnShareWithDoc(val idToShare: Int, val sharedWithDoctor: Boolean) :
        NewNoteEvent

    data class EmotionsChange(val emotion: Emotion, val clarifyEmotions: List<Mood>) : NewNoteEvent
    data class SaveNote(
        val id: Int,
        val eventDetails: String,
        val thoughtsAnalysis: String,
        val emotionType: String,
        val physicalSensations: String,
        val primaryEmotion: Emotion,
        val clarifyEmotions: List<Mood>,
        val shareWithDoc: Boolean
    ) : NewNoteEvent

    data object OnClearClick : NewNoteEvent
}