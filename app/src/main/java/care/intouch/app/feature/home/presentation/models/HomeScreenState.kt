package care.intouch.app.feature.home.presentation.models

data class HomeScreenState (
    val isLoadings: Boolean = false,
    val taskList: List<Task> = emptyList(),
    val diaryList: List<DiaryEntry> = emptyList()
)