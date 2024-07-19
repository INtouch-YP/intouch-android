package care.intouch.app.feature.home.presentation.models

import care.intouch.app.feature.home.domain.models.DiaryEntry
import care.intouch.app.feature.home.domain.models.Task

data class HomeUiState(
    val taskList: List<Task> = emptyList(),
    val diaryList: List<DiaryEntry> = emptyList(),
    val isSeeAllPlanVisible: Boolean = taskList.isNotEmpty(),
    val isDiaryListVisible: Boolean = diaryList.isNotEmpty(),
    val isLoading: Boolean = false,
    val isConnectionLost: Boolean = false,
    val userName: String = "Bob"
)