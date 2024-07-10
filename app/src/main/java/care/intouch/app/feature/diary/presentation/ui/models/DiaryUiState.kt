package care.intouch.app.feature.diary.presentation.ui.models

data class DiaryUiState(
    val diaryList: List<DiaryEntry> = emptyList(),
    val isDiaryListVisible: Boolean = diaryList.isNotEmpty(),
    val isDiaryListEmpty: Boolean = diaryList.isNotEmpty(),
    val isLoading: Boolean = false,
    val userName: String = "",
)