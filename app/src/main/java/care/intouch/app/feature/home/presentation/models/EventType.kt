package care.intouch.app.feature.home.presentation.models

sealed interface EventType {
    data class ClearTask(val taskId: Int) : EventType

    data class ShareTask(
        val taskId: Int,
        val index: Int,
        val isSharedWithDoctor: Boolean
    ) : EventType

    data class ShareDiaryEntry(
        val diaryEntryId: Int,
        val index: Int,
        val isSharedWithDoctor: Boolean
    ) : EventType

    data class DeleteDiaryEntry(val diaryEntryId: Int) : EventType

    data object SendRequestAgain : EventType
    data object RefreshScreen : EventType
}