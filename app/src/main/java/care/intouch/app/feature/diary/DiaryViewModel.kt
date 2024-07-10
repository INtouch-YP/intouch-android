package care.intouch.app.feature.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.R
import care.intouch.app.feature.diary.presentation.ui.models.DiaryChangeEvent
import care.intouch.app.feature.diary.presentation.ui.models.DiaryDataState
import care.intouch.app.feature.diary.presentation.ui.models.DiaryEntry
import care.intouch.app.feature.diary.presentation.ui.models.DiaryPopUp
import care.intouch.app.feature.diary.presentation.ui.models.DiaryUiState
import care.intouch.app.feature.diary.presentation.ui.models.Mood
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
class DiaryViewModel @Inject constructor() : ViewModel() {
    private val _diaryUIState = MutableStateFlow(DiaryUiState())
    val diaryUIState: StateFlow<DiaryUiState> = _diaryUIState.asStateFlow()
    private val _diaryDataState = MutableStateFlow(DiaryDataState())
    val diaryDataState: StateFlow<DiaryDataState> = _diaryDataState.asStateFlow()
    private val _sideEffect = MutableSharedFlow<DiaryPopUp>()
    val sideEffect: SharedFlow<DiaryPopUp> = _sideEffect.asSharedFlow()

    init {
        loadDiaryData()
        setUserName()

        viewModelScope.launch {
            diaryDataState.collect { state ->
                _diaryUIState.update {
                    it.copy(
                        diaryList = state.noteList
                    )
                }
            }
        }
    }

    private fun getState(): DiaryDataState = _diaryDataState.value

    fun onEvent(event: DiaryChangeEvent) {
        when (event) {
            is DiaryChangeEvent.OnShareWithDoc -> {
                onShareToggle(
                    index = event.index,
                    event.sharedWithDoctor
                )

            }

            is DiaryChangeEvent.IntentionToDelete -> {
                showDeletePopup(
                    index = event.index
                )
            }
        }
    }

    private fun showDeletePopup(index: Int) {
        showPopup(
            title = StringVO.Resource(R.string.info_delete_node_question),
            massage = StringVO.Resource(R.string.warning_delete),
            onConfirmButtonText = StringVO.Resource(R.string.confirm_button),
            onDismissButtonText = StringVO.Resource(R.string.cancel_button),
            onConfirm = {
                handleDeleteDiaryEntry(index)
            },
            onDismiss = {}
        )
    }

    private fun handleDeleteDiaryEntry(index: Int) {
        val newDiaryList = getState().noteList.toMutableList()
        newDiaryList.removeAt(index)
        _diaryDataState.update {
            it.copy(noteList = newDiaryList)
        }
    }

    private fun onShareToggle(index: Int, isShared: Boolean) {
        val shareNote = getState()
            .noteList[index]
            .copy(sharedWithDoc = isShared)
        val diaryList = getState().noteList.toMutableList()
        diaryList[index] = shareNote

        _diaryDataState.update { state ->
            state.copy(noteList = diaryList)
        }

    }

    private fun showPopup(
        title: StringVO,
        massage: StringVO,
        onConfirmButtonText: StringVO,
        onDismissButtonText: StringVO,
        onConfirm: () -> Unit,
        onDismiss: () -> Unit
    ) {
        viewModelScope.launch {
            _sideEffect.emit(
                DiaryPopUp.ShowPopUp(
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

    private fun setUserName() {
        val name = buildString {
            append("Bober Jan")
        }
        _diaryUIState.update {
            it.copy(userName = name)
        }
    }

    private fun loadDiaryData() {
        val count = kotlin.random.Random.nextInt(1, 6)
        val entries = generateRandomDiaryEntries(count)
        _diaryDataState.update {
            it.copy(noteList = entries)
        }
    }

    private fun generateRandomDiaryEntries(count: Int): List<DiaryEntry> {
        val random = kotlin.random.Random
        val moods = listOf(
            Mood(name = "bad"),
            Mood(name = "Sad"),
            Mood(name = "Angry"),
            Mood(name = "Excited")
        )
        val notes =
            listOf(
                "Went for a walk",
                "Had a great meal",
                "Felt stressed",
                "Met with friends",
                "Thers once was a ship that put to see and the name"
            )
        val month = listOf("jan", "mar", "oct", "nov")

        return List(count) { _ ->
            DiaryEntry(
                id = random.nextInt(100),
                data = "${random.nextInt(31)} ${month[random.nextInt(month.size)]}",
                note = notes[random.nextInt(notes.size)],
                moodList = moods,
                sharedWithDoc = random.nextBoolean(),
            )
        }
    }
}