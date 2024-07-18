package care.intouch.app.feature.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.R
import care.intouch.app.feature.authorization.domain.useCase.GetUserFullNameUseCase
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.diary.domain.modal.Diary
import care.intouch.app.feature.diary.domain.useCase.DeleteDiaryUC
import care.intouch.app.feature.diary.domain.useCase.GetDiariesUC
import care.intouch.app.feature.diary.domain.useCase.SwitchVisibleUC
import care.intouch.app.feature.diary.presentation.ui.mapperToDiaryEntry
import care.intouch.app.feature.diary.presentation.ui.models.DiaryChangeEvent
import care.intouch.app.feature.diary.presentation.ui.models.DiaryDataState
import care.intouch.app.feature.diary.presentation.ui.models.DiaryPopUp
import care.intouch.app.feature.diary.presentation.ui.models.DiaryUiState
import care.intouch.uikit.common.StringVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
class DiaryViewModel @Inject constructor(
    private val getDiariesUC: GetDiariesUC,
    private val deleteDiaryUC: DeleteDiaryUC,
    private val switchVisibleUC: SwitchVisibleUC,
    private val getUserFullNameUseCase: GetUserFullNameUseCase
) : ViewModel() {
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
                    event.sharedWithDoctor,
                    id = event.idToShare
                )

            }

            is DiaryChangeEvent.IntentionToDelete -> {
                showDeletePopup(
                    index = event.index,
                    id = event.idToDelete
                )
            }
        }
    }

    private fun showDeletePopup(index: Int, id: Int) {
        showPopup(
            title = StringVO.Resource(R.string.info_delete_node_question),
            massage = StringVO.Resource(R.string.warning_delete),
            onConfirmButtonText = StringVO.Resource(R.string.confirm_button),
            onDismissButtonText = StringVO.Resource(R.string.cancel_button),
            onConfirm = {
                handleDeleteDiaryEntry(index, id)
            },
            onDismiss = {}
        )
    }

    private fun handleDeleteDiaryEntry(index: Int, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (deleteDiaryUC.invoke(id)) {

                is Resource.Success -> {
                    val newDiaryList = getState().noteList.toMutableList()
                    newDiaryList.removeAt(index)
                    _diaryDataState.update {
                        it.copy(noteList = newDiaryList)
                    }
                }

                is Resource.Error -> {}

            }
        }
    }

    private fun onShareToggle(index: Int, isShared: Boolean, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = switchVisibleUC.invoke(id)) {

                is Resource.Success -> {
                    val shareNote = getState()
                        .noteList[index]
                        .copy(sharedWithDoc = isShared)
                    val diaryList = getState().noteList.toMutableList()
                    diaryList[index] = shareNote
                    _diaryDataState.update { state ->
                        state.copy(noteList = diaryList)
                    }
                }

                is Resource.Error -> {
                }

            }
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
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = getUserFullNameUseCase.invoke()){
                is Resource.Success -> {
                    _diaryUIState.update {
                        it.copy(userName = result.data)
                    }
                }
                is Resource.Error -> TODO()
            }
        }
    }


    private fun loadDiaryData() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = getDiariesUC.invoke()) {

                is Resource.Success -> {
                    _diaryDataState.update {
                        it.copy(noteList = result.data.map { diary: Diary ->
                            mapperToDiaryEntry(diary)
                        })
                    }
                }

                is Resource.Error -> {
                    _diaryDataState.update {
                        it.copy(noteList = emptyList())
                    }
                }

            }
        }
    }
}