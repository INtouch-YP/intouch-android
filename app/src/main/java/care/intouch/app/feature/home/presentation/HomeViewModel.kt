package care.intouch.app.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.R
import care.intouch.app.feature.home.presentation.models.DiaryEntry
import care.intouch.app.feature.home.presentation.models.EventType
import care.intouch.app.feature.home.presentation.models.HomeScreenSideEffect
import care.intouch.app.feature.home.presentation.models.HomeScreenState
import care.intouch.app.feature.home.presentation.models.HomeUiState
import care.intouch.app.feature.home.presentation.models.Mood
import care.intouch.app.feature.home.presentation.models.Status
import care.intouch.app.feature.home.presentation.models.Task
import care.intouch.uikit.common.StringVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _stateScreen = MutableStateFlow(HomeScreenState())
    private val stateScreen: StateFlow<HomeScreenState> = _stateScreen.asStateFlow()
    private val _homeUIState = MutableStateFlow(HomeUiState())
    val homeUIState: StateFlow<HomeUiState> = _homeUIState.asStateFlow()
    private val _sideEffect = MutableSharedFlow<HomeScreenSideEffect>()
    val sideEffect: SharedFlow<HomeScreenSideEffect> = _sideEffect.asSharedFlow()

    init {
        fetchTasks()
        fetchDiary()

        viewModelScope.launch {
            stateScreen.collect { state ->
                _homeUIState.update {
                    it.copy(
                        taskList = state.taskList,
                        diaryList = state.diaryList
                    )
                }
            }
        }
    }

    private fun getState(): HomeScreenState = _stateScreen.value

    fun executeEvent(event: EventType) {
        when (event) {
            is EventType.DuplicateTask -> {
                duplicateTask(
                    taskId = event.taskId,
                    index = event.index
                )
            }

            is EventType.ClearTask -> {
                clearTask(
                    taskId = event.taskId,
                    index = event.index
                )
            }

            is EventType.ShareTask -> {
                shareTask(
                    taskId = event.taskId,
                    index = event.index, event.isSharedWithDoctor
                )
            }

            is EventType.ShareDiaryEntry -> {
                shareDiaryEntry(
                    diaryEntryId = event.diaryEntryId,
                    index = event.index,
                    isShared = event.isSharedWithDoctor
                )
            }

            is EventType.DeleteDiaryEntry -> {
                deleteDiaryEntry(
                    diaryId = event.diaryEntryId,
                    index = event.index
                )
            }
        }
    }

    private fun duplicateTask(taskId: Int, index: Int) {
        val newItem = getState().taskList[index]
        val newTaskList = getState().taskList.toMutableList()
        newTaskList.add(newItem)
        _stateScreen.update {
            it.copy(taskList = newTaskList)
        }
    }

    private fun clearTask(taskId: Int, index: Int) {
        showDialog(
            title = StringVO.Resource(R.string.info_delete_task_question),
            massage = StringVO.Resource(R.string.warning_delete),
            onConfirmButtonText = StringVO.Resource(R.string.confirm_button),
            onDismissButtonText = StringVO.Resource(R.string.cancel_button),
            onConfirm = {
                handleClearTask(taskId, index)
            },
            onDismiss = {}
        )
    }

    private fun handleClearTask(taskId: Int, index: Int) {
        val newTaskList = getState().taskList.toMutableList()
        newTaskList[index] = newTaskList[index]
            .copy(
                isSharedWithDoctor = false,
                status = Status.TO_DO,
                description = ""
            )
        _stateScreen.update {
            it.copy(taskList = newTaskList)
        }
    }

    private fun shareTask(taskId: Int, index: Int, shareStatus: Boolean) {
        val sharedTask = getState()
            .taskList[index]
            .copy(isSharedWithDoctor = shareStatus)
        val taskList = getState().taskList.toMutableList()
        taskList[index] = sharedTask

        _stateScreen.update { state ->
            state.copy(taskList = taskList)
        }
    }

    private fun shareDiaryEntry(diaryEntryId: Int, index: Int, isShared: Boolean) {
        val sharedTask = getState()
            .diaryList[index]
            .copy(isSharedWithDoctor = isShared)
        val diaryList = getState().diaryList.toMutableList()
        diaryList[index] = sharedTask

        _stateScreen.update { state ->
            state.copy(diaryList = diaryList)
        }
    }

    private fun deleteDiaryEntry(diaryId: Int, index: Int) {
        showDialog(
            title = StringVO.Resource(R.string.info_delete_node_question),
            massage = StringVO.Resource(R.string.warning_delete),
            onConfirmButtonText = StringVO.Resource(R.string.confirm_button),
            onDismissButtonText = StringVO.Resource(R.string.cancel_button),
            onConfirm = {
                handleDeleteDiaryEntry(diaryId, index)
            },
            onDismiss = {}
        )
    }

    private fun handleDeleteDiaryEntry(diaryId: Int, index: Int) {
        val newDiaryList = getState().diaryList.toMutableList()
        newDiaryList.removeAt(index)
        _stateScreen.update {
            it.copy(diaryList = newDiaryList)
        }
    }

    private fun fetchTasks() {
        val taskList = listOf(
            Task(
                id = 1,
                status = Status.IN_PROGRESS,
                isSharedWithDoctor = false,
                description = "Невероятно длинный текст, который не должен поместиться на экране, а в конце должны быть точески"
            ),
            Task(
                id = 1,
                status = Status.IN_PROGRESS,
                isSharedWithDoctor = false,
                description = "aboba Невероятно длинный текст, который не должен поместиться на экране, а в конце должны быть точески"
            ),
            Task(
                id = 1,
                status = Status.IN_PROGRESS,
                isSharedWithDoctor = false,
                description = "aboba"
            )
        )
        _stateScreen.update {
            it.copy(taskList = taskList)
        }
    }

    private fun fetchDiary() {
        val diaryList = listOf(
            DiaryEntry(
                id = 1,
                data = "13, jul",
                note = "Lorem Ipsum dolor sit amet Lorem Ipsum... ",
                moodList = listOf(Mood(name = "Bad")),
                isSharedWithDoctor = false
            ),
            DiaryEntry(
                id = 1,
                data = "13, jul",
                note = "Lorem Ipsum dolor sit amet Lorem Ipsum... ",
                moodList = listOf(Mood(name = "Bad")),
                isSharedWithDoctor = false
            )
        )
        _stateScreen.update {
            it.copy(diaryList = diaryList)
        }
    }

    private fun showDialog(
        title: StringVO,
        massage: StringVO,
        onConfirmButtonText: StringVO,
        onDismissButtonText: StringVO,
        onConfirm: () -> Unit,
        onDismiss: () -> Unit
    ) {
        viewModelScope.launch {
            _sideEffect.emit(
                HomeScreenSideEffect.ShowDialog(
                    title = title,
                    massage = massage,
                    onConfirmButtonText = onConfirmButtonText,
                    onDismissButtonText = onDismissButtonText,
                    onConfirm = onConfirm,
                    onDismiss = onDismiss
                )
            )
        }
    }
}





