package care.intouch.app.feature.home.presentation.models

import care.intouch.app.feature.home.domain.models.DiaryEntry
import care.intouch.app.feature.home.domain.models.Task

data class HomeScreenState(
    val isLoading: Boolean = false,
    val taskList: List<Task> = emptyList(),
    val diaryList: List<DiaryEntry> = emptyList(),
    val userInformation: UserInformation = UserInformation(),
    val isConnectionLost: Boolean = false
)