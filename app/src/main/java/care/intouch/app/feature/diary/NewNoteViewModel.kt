package care.intouch.app.feature.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.diary.presentation.ui.models.DiaryEntry
import care.intouch.app.feature.diary.presentation.ui.models.Mood
import care.intouch.app.feature.diary.presentation.ui.models.newnote.Emotion
import care.intouch.app.feature.diary.presentation.ui.models.newnote.NewNoteDataState
import care.intouch.app.feature.diary.presentation.ui.models.newnote.NewNoteEvent
import care.intouch.app.feature.diary.presentation.ui.models.newnote.NewNoteUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class NewNoteViewModel @Inject constructor() : ViewModel() {
    private val _newNoteUIState = MutableStateFlow(NewNoteUIState())
    val newNoteUIState: StateFlow<NewNoteUIState> = _newNoteUIState.asStateFlow()
    private val _newNoteDataState = MutableStateFlow(NewNoteDataState())
    val newNoteDataState: StateFlow<NewNoteDataState> = _newNoteDataState.asStateFlow()

    init {


        viewModelScope.launch {
            newNoteDataState.collect { state ->
                _newNoteUIState.update {
                    it.copy(
                        primaryEmotion = state.primaryEmotion,
                        clarifyEmotions = state.clarifyEmotions,
                    )
                }
            }
        }
    }

    private fun getState(): NewNoteDataState = _newNoteDataState.value

    fun onEvent(event: NewNoteEvent) {
        when (event) {
            is NewNoteEvent.EventDetailsTextChange -> {
                viewModelScope.launch {
                    _newNoteDataState.update { currentState ->
                        currentState.copy(eventDetails = event.text)
                    }
                }
            }

            is NewNoteEvent.OnShareWithDoc -> {
                viewModelScope.launch {
                    _newNoteDataState.update { currentState ->
                        currentState.copy(shareWithDoc = !currentState.shareWithDoc)
                    }
                }
            }

            is NewNoteEvent.ThoughtsAnalysisTextChange -> {
                viewModelScope.launch {
                    _newNoteDataState.update { currentState ->
                        currentState.copy(thoughtsAnalysis = event.text)
                    }
                }
            }

            is NewNoteEvent.EmotionalTypeTextChange -> {
                viewModelScope.launch {
                    _newNoteDataState.update { currentState ->
                        currentState.copy(emotionType = event.text)
                    }
                }
            }

            is NewNoteEvent.PhysicalSensationsTextChange -> {
                viewModelScope.launch {
                    _newNoteDataState.update { currentState ->
                        currentState.copy(physicalSensations = event.text)
                    }
                }
            }

            is NewNoteEvent.SaveNote -> {
                saveNote()
            }

            is NewNoteEvent.EmotionsChange -> {
                updatePrimaryEmotion(event.emotion, event.clarifyEmotions)
            }

            is NewNoteEvent.OnClearClick -> {
                onTrashClick()
            }
        }
    }

    private fun saveNote() {
        viewModelScope.launch {
            val newNote = DiaryEntry(
                id = Random.nextInt(100),
                data = Date().toString(),
                moodList = getState().clarifyEmotions,
                note = getState().eventDetails,
                sharedWithDoc = false
            )
        }

    }

    private fun updatePrimaryEmotion(newEmotion: Emotion, clarifyEmotions: List<Mood>) {
        viewModelScope.launch {
            _newNoteDataState.update { currentState ->
                currentState.copy(primaryEmotion = newEmotion, clarifyEmotions = clarifyEmotions)
            }
        }
    }

    private fun onTrashClick() {
        viewModelScope.launch {
            _newNoteDataState.update { currentState ->
                currentState.copy(
                    eventDetails = "",
                    thoughtsAnalysis = "",
                    emotionType = "",
                    physicalSensations = "",
                    primaryEmotion = Emotion.BLANC,
                    clarifyEmotions = emptyList()
                )
            }
        }
    }

    private fun onShareToggle(isShared: Boolean) {
        _newNoteDataState.update { state ->
            state.copy(shareWithDoc = isShared)
        }
    }
}