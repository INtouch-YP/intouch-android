package care.intouch.app.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.R
import care.intouch.app.feature.common.data.models.exception.NetworkException
import care.intouch.app.feature.home.domain.use_case.AssignmentsInteractor
import care.intouch.app.feature.home.domain.use_case.DiaryEntriesInteractor
import care.intouch.app.feature.home.domain.use_case.GetDiaryEntries
import care.intouch.app.feature.home.domain.use_case.GetTasks
import care.intouch.app.feature.home.domain.use_case.GetUserInformation
import care.intouch.app.feature.home.presentation.models.EventType
import care.intouch.app.feature.home.presentation.models.HomeScreenSideEffect
import care.intouch.app.feature.home.presentation.models.HomeScreenState
import care.intouch.app.feature.home.presentation.models.HomeUiState
import care.intouch.uikit.common.StringVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import care.intouch.uikit.R as UikitR

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val assignmentsInteractor: AssignmentsInteractor,
    private val diaryEntriesInteractor: DiaryEntriesInteractor,
    private val getDiaryEntries: GetDiaryEntries,
    private val getTasks: GetTasks,
    private val getUserInformation: GetUserInformation
) : ViewModel() {
    private val _stateScreen = MutableStateFlow(HomeScreenState())
    private val stateScreen: StateFlow<HomeScreenState> = _stateScreen.asStateFlow()
    private val _homeUIState = MutableStateFlow(HomeUiState())
    val homeUIState: StateFlow<HomeUiState> = _homeUIState.asStateFlow()
    private val _sideEffect = MutableSharedFlow<HomeScreenSideEffect>()
    val sideEffect: SharedFlow<HomeScreenSideEffect> = _sideEffect.asSharedFlow()
    private var homeJob: Job? = null

    init {
        fetchUserInformation()
        refreshScreenInformation(
            userId = _stateScreen.value.userInformation.userId
        )

        viewModelScope.launch {
            stateScreen.collect { state ->
                _homeUIState.update {
                    it.copy(
                        taskList = state.taskList,
                        diaryList = state.diaryList,
                        isSeeAllPlanVisible = state.taskList.isNotEmpty(),
                        isDiaryListVisible = state.diaryList.isNotEmpty(),
                        userName = state.userInformation.userName,
                        isLoading = state.isLoading,
                        isConnectionLost = state.isConnectionLost
                    )
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        homeJob = null
    }

    private fun getState(): HomeScreenState = _stateScreen.value

    fun executeEvent(event: EventType) {
        when (event) {
            is EventType.ClearTask -> {
                clearTask(taskId = event.taskId)
            }

            is EventType.ShareTask -> {
                shareTask(
                    taskId = event.taskId,
                    index = event.index,
                    shareStatus = event.isSharedWithDoctor
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
                    diaryId = event.diaryEntryId
                )
            }

            is EventType.SendRequestAgain -> {
                sendRequestAgain()
            }

            is EventType.RefreshScreen -> {
                refreshScreenInformation(
                    userId = _stateScreen.value.userInformation.userId
                )
            }
        }
    }

    private fun clearTask(taskId: Int) {
        showDialog(
            header = StringVO.Resource(R.string.info_delete_task_question),
            massage = StringVO.Resource(R.string.warning_delete),
            onConfirmButtonText = StringVO.Resource(R.string.confirm_button),
            onDismissButtonText = StringVO.Resource(R.string.cancel_button),
            onConfirm = {
                handleClearTask(taskId)
            },
            onDismiss = {}
        )
    }

    private fun handleClearTask(taskId: Int) {
        _stateScreen.update { state ->
            state.copy(isLoading = true)
        }
        viewModelScope.launch(Dispatchers.IO) {
            assignmentsInteractor.clearAssignment(assignmentId = taskId)
                .onSuccess {
                    refreshTasks()
                }
                .onFailure { exception ->
                    when (exception) {
                        is NetworkException.NoInternetConnection -> {
                            showToast(
                                massage = StringVO.Resource(R.string.problem_with_connection),
                                onDismiss = {}
                            )
                        }

                        else -> {
                            showToast(
                                massage = StringVO.Resource(R.string.toast_failure_delete_diary_entry),
                                onDismiss = {}
                            )
                        }
                    }
                }
            _stateScreen.update { state ->
                state.copy(isLoading = false)
            }
        }

    }

    private fun shareTask(taskId: Int, index: Int, shareStatus: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            assignmentsInteractor.shareTaskWithDoctor(assignmentId = taskId)
                .onSuccess {
                    val sharedTask = getState()
                        .taskList[index]
                        .copy(isSharedWithDoctor = shareStatus)
                    val taskList = getState().taskList.toMutableList()
                    taskList[index] = sharedTask

                    _stateScreen.update { state ->
                        state.copy(taskList = taskList)
                    }
                    showToast(
                        massage = StringVO.Resource(R.string.toast_success_share_task),
                        onDismiss = {}
                    )
                }
                .onFailure { exception ->
                    when (exception) {
                        is NetworkException.NoInternetConnection -> showToast(
                            massage = StringVO.Resource(R.string.problem_with_connection),
                            onDismiss = {}
                        )

                        else -> showToast(
                            massage = StringVO.Resource(R.string.toast_failure_share_task),
                            onDismiss = {}
                        )
                    }
                }
        }
    }

    private fun shareDiaryEntry(diaryEntryId: Int, index: Int, isShared: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            diaryEntriesInteractor.shareDiaryEntryWithDoctor(diaryNoteId = diaryEntryId)
                .onSuccess {
                    val sharedTask = getState()
                        .diaryList[index]
                        .copy(isSharedWithDoctor = isShared)
                    val diaryList = getState().diaryList.toMutableList()
                    diaryList[index] = sharedTask

                    _stateScreen.update { state ->
                        state.copy(diaryList = diaryList)
                    }
                    showToast(
                        massage = StringVO.Resource(R.string.toast_success_share_diary_entry),
                        onDismiss = {}
                    )
                }
                .onFailure { exception ->
                    when (exception) {
                        is NetworkException.NoInternetConnection -> showToast(
                            massage = StringVO.Resource(R.string.problem_with_connection),
                            onDismiss = {}
                        )

                        else -> showToast(
                            massage = StringVO.Resource(R.string.toast_failure_share_diary_entry),
                            onDismiss = {}
                        )
                    }
                }
        }

    }

    private fun deleteDiaryEntry(diaryId: Int) {
        showDialog(
            header = StringVO.Resource(R.string.info_delete_node_question),
            massage = StringVO.Resource(R.string.warning_delete),
            onConfirmButtonText = StringVO.Resource(R.string.confirm_button),
            onDismissButtonText = StringVO.Resource(R.string.cancel_button),
            onConfirm = {
                handleDeleteDiaryEntry(diaryId)
            },
            onDismiss = {}
        )
    }

    private fun handleDeleteDiaryEntry(diaryId: Int) {
        _stateScreen.update { state ->
            state.copy(isLoading = true)
        }
        viewModelScope.launch(Dispatchers.IO) {
            diaryEntriesInteractor.deleteDiaryEntry(diaryNoteId = diaryId)
                .onSuccess {
                    refreshDiary()
                }
                .onFailure { exception ->
                    when (exception) {
                        is NetworkException.NoInternetConnection -> {
                            showToast(
                                massage = StringVO.Resource(R.string.problem_with_connection),
                                onDismiss = {}
                            )
                        }

                        else -> {
                            showToast(
                                massage = StringVO.Resource(R.string.toast_failure_delete_diary_entry),
                                onDismiss = {}
                            )
                        }
                    }
                }
            _stateScreen.update { state ->
                state.copy(isLoading = false)
            }
        }
    }

    private fun fetchUserInformation() {
        viewModelScope.launch(Dispatchers.IO) {
            val userInformation = getUserInformation.execute()
            _stateScreen.update { state ->
                state.copy(userInformation = userInformation)
            }
        }
    }

    private fun sendRequestAgain() {
        homeJob?.start()
    }

    private fun refreshDiary() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchDiary(_stateScreen.value.userInformation.userId)
            _stateScreen.update { state ->
                state.copy(isLoading = false)
            }
        }
    }

    private fun refreshTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchTasks(_stateScreen.value.userInformation.userId)
            _stateScreen.update { state ->
                state.copy(isLoading = false)
            }
        }
    }

    private fun refreshScreenInformation(userId: Int) {
        homeJob = viewModelScope.launch(Dispatchers.IO) {
            _stateScreen.update { state ->
                state.copy(isLoading = true)
            }
            fetchTasks(userId = userId)
            fetchDiary(userId = userId)
            _stateScreen.update { state ->
                state.copy(isLoading = false)
            }
        }
    }

    private suspend fun fetchTasks(userId: Int) {
        getTasks.getTasks(userId)
            .onSuccess { task ->
                _stateScreen.update { state ->
                    state.copy(taskList = task)
                }
            }
            .onFailure { exception ->
                when (exception) {
                    is NetworkException.NoInternetConnection -> {
                        _stateScreen.update { state ->
                            state.copy(isConnectionLost = true)
                        }
                        showInternetConnectionDialog(
                            header = StringVO.Resource(R.string.no_internet_connection_header),
                            message = StringVO.Resource(R.string.no_internet_connection_message),
                            image = UikitR.drawable.illustration_internet_connection_lost,
                            onConfirmButtonText = StringVO.Resource(R.string.try_again),
                            onConfirm = {
                                _stateScreen.update { state ->
                                    state.copy(isConnectionLost = false)
                                }
                                sendRequestAgain()
                            }
                        )
                    }

                    else -> {
                        showToast(
                            massage = StringVO.Resource(R.string.server_error),
                            onDismiss = {}
                        )
                    }
                }
            }
    }


    private suspend fun fetchDiary(userId: Int) {
        getDiaryEntries.execute(userId)
            .onSuccess { diaryEntries ->
                _stateScreen.update { state ->
                    state.copy(diaryList = diaryEntries)
                }
            }
            .onFailure { exception ->
                when (exception) {
                    is NetworkException.NoInternetConnection -> {
                        _stateScreen.update { state ->
                            state.copy(isConnectionLost = true)
                        }
                        showInternetConnectionDialog(
                            header = StringVO.Resource(R.string.no_internet_connection_header),
                            message = StringVO.Resource(R.string.no_internet_connection_message),
                            image = UikitR.drawable.illustration_internet_connection_lost,
                            onConfirmButtonText = StringVO.Resource(R.string.try_again),
                            onConfirm = {
                                _stateScreen.update { state ->
                                    state.copy(isConnectionLost = false)
                                }
                                sendRequestAgain()
                            }
                        )
                    }

                    else -> {
                        showToast(
                            massage = StringVO.Resource(R.string.server_error),
                            onDismiss = {}
                        )
                    }
                }
            }
    }

    private fun showDialog(
        header: StringVO,
        massage: StringVO,
        onConfirmButtonText: StringVO,
        onDismissButtonText: StringVO,
        onConfirm: () -> Unit,
        onDismiss: () -> Unit
    ) {
        viewModelScope.launch {
            _sideEffect.emit(
                HomeScreenSideEffect.ShowDialog(
                    header = header,
                    message = massage,
                    onConfirmButtonText = onConfirmButtonText,
                    onDismissButtonText = onDismissButtonText,
                    onConfirm = onConfirm,
                    onDismiss = onDismiss
                )
            )
        }
    }

    private fun showInternetConnectionDialog(
        header: StringVO,
        message: StringVO,
        image: Int,
        onConfirmButtonText: StringVO,
        onConfirm: () -> Unit,
    ) {
        viewModelScope.launch {
            _sideEffect.emit(
                HomeScreenSideEffect.ShowDialog(
                    header = header,
                    message = message,
                    image = image,
                    onConfirmButtonText = onConfirmButtonText,
                    onConfirm = onConfirm,
                )
            )
        }
    }

    private fun showToast(massage: StringVO, onDismiss: () -> Unit) {
        viewModelScope.launch {
            _sideEffect.emit(
                HomeScreenSideEffect.ShowToast(
                    message = massage,
                    onDismiss = onDismiss
                )
            )
        }
    }

}





