package care.intouch.app.feature.home.presentation.models

data class HomeUiState(
    val taskList: List<Task> = emptyList(),
    val diaryList: List<DiaryEntry> = emptyList(),
    val isSeeAllPlanVisible: Boolean = taskList.isNotEmpty(),
    val isSeeAllPlanEmpty: Boolean = taskList.isNotEmpty(),
    val isDiaryListVisible: Boolean = diaryList.isNotEmpty(),
    val isDiaryListEmpty: Boolean = diaryList.isNotEmpty(),
    val isLoading: Boolean = false
)