package care.intouch.app.feature.diary.presentation.ui.models

sealed interface DiaryChangeEvent {
    data class IntentionToDelete(val idToDelete: Int, val index: Int) : DiaryChangeEvent
    data class OnShareWithDoc(val idToShare: Int, val index: Int, val sharedWithDoctor: Boolean) :
        DiaryChangeEvent
}