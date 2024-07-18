package care.intouch.app.feature.diary.presentation.ui.fillingOutScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.diary.domain.modal.Diary
import care.intouch.app.feature.diary.domain.useCase.ClearAllInformationUC
import care.intouch.app.feature.diary.domain.useCase.ClearEmotionsUC
import care.intouch.app.feature.diary.domain.useCase.GetEmotionDescUC
import care.intouch.app.feature.diary.domain.useCase.GetEmotionUC
import care.intouch.app.feature.diary.domain.useCase.GetSavedAnswers
import care.intouch.app.feature.diary.domain.useCase.SaveAnswersUC
import care.intouch.app.feature.diary.domain.useCase.SaveDiaryUC
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.models.EmotionDescriptionTask
import care.intouch.app.feature.diary.presentation.ui.fillingOutScreen.getEmotionDesc
import care.intouch.app.feature.diary.presentation.ui.fillingOutScreen.models.FillingOutDataEvent
import care.intouch.app.feature.diary.presentation.ui.fillingOutScreen.models.FillingOutScreenState
import care.intouch.uikit.common.ImageVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FillingOutViewModel @Inject constructor(
    private val getEmotionUC: GetEmotionUC,
    private val getEmotionDescUC: GetEmotionDescUC,
    private val clearEmotionsUC: ClearEmotionsUC,
    private val saveAnswersUC: SaveAnswersUC,
    private val getSavedAnswers: GetSavedAnswers,
    private val saveDiaryUC: SaveDiaryUC,
    private val clearAllInformationUC: ClearAllInformationUC
) : ViewModel() {
    private val _uiState = MutableStateFlow(FillingOutScreenState())
    val uiState = _uiState.asStateFlow()
    private val _sharedImageUiState = MutableSharedFlow<ImageVO?>()
    val sharedImageUiState = _sharedImageUiState.asSharedFlow()
    private val _sharedListUiState = MutableSharedFlow<List<EmotionDescriptionTask>>()
    val sharedListUiState = _sharedListUiState.asSharedFlow()
    private val _sharedNavigateState = MutableSharedFlow<Boolean>()
    val sharedNavigateState = _sharedNavigateState.asSharedFlow()
    private val _sharedUiState = MutableSharedFlow<FillingOutScreenState>()
    val sharedUiState = _sharedUiState.asSharedFlow()

    init {
        getEmotionDescription()
        getEmotion()
        getSavedAnswers()
    }

    fun onEvent(event: FillingOutDataEvent) {
        when (event) {
            is FillingOutDataEvent.OnAnalysisTextChanged -> updateAnalysisState(event.text)
            is FillingOutDataEvent.OnDetailsTextChanged -> updateDetailsState(event.text)
            is FillingOutDataEvent.OnSensationsTextChanged -> updateSensationsState(event.text)
            is FillingOutDataEvent.OnTypeTextChanged -> updateTypeState(event.text)
            FillingOutDataEvent.OnUpdateStateChanged -> updateStates()
            FillingOutDataEvent.OnTrashClicked -> clearEmotions()
            FillingOutDataEvent.OnAddEmotionClicked -> navigateToEmotionChoice()
            FillingOutDataEvent.OnChangeVisible -> changeVisible()
            FillingOutDataEvent.OnClickSave -> saveDiary()
        }
    }

    private fun saveDiary() {
        viewModelScope.launch(Dispatchers.IO) {
            saveDiaryUC.invoke(
                Diary(
                    eventDetails = _uiState.value.detailsText,
                    thoughtsAnalysis = _uiState.value.analysisText,
                    physicalSensations = _uiState.value.sensationsText,
                    emotionType = _uiState.value.typeText,
                    visible = _uiState.value.visible,
                    primaryEmotion = getEmotionUC.invoke(),
                    clarifyingEmotion = getEmotionDescUC.invoke().map { it.nameEmotion }
                )
            )
            clearAllInformationUC.invoke()
        }
    }

    private fun getSavedAnswers() {
        viewModelScope.launch {
            val result = getSavedAnswers.invoke()
            _uiState.emit(FillingOutScreenState(result[0], result[1], result[2], result[3]))
        }
    }

    private fun navigateToEmotionChoice() {
        viewModelScope.launch {
            saveAnswersUC.invoke(
                uiState.value.detailsText,
                uiState.value.analysisText,
                uiState.value.typeText,
                uiState.value.sensationsText
            )
            _sharedNavigateState.emit(true)
        }
    }

    private fun updateStates() {
        getEmotion()
        getEmotionDescription()
    }

    private fun clearEmotions() {
        viewModelScope.launch {
            clearEmotionsUC.invoke()
            _sharedImageUiState.emit(null)
            _sharedListUiState.emit(listOf())
        }
    }

    private fun getEmotion() {
        var resultEmotion: String
        var resultEmotionImage: ImageVO? = null
        viewModelScope.launch {
            resultEmotion = getEmotionUC.invoke()
            if (resultEmotion.isNotEmpty()) {
                when (resultEmotion) {
                    "TERRIBLE" -> {
                        resultEmotionImage =
                            ImageVO.Resource(care.intouch.uikit.R.drawable.icon_terrible_small_active)
                    }

                    "BAD" -> {
                        resultEmotionImage =
                            ImageVO.Resource(care.intouch.uikit.R.drawable.icon_bad_small_active)
                    }

                    "OKAY" -> {
                        resultEmotionImage =
                            ImageVO.Resource(care.intouch.uikit.R.drawable.icon_okay_small_active)
                    }

                    "GOOD" -> {
                        resultEmotionImage =
                            ImageVO.Resource(care.intouch.uikit.R.drawable.icon_good_small_active)
                    }

                    "GREAT" -> {
                        resultEmotionImage =
                            ImageVO.Resource(care.intouch.uikit.R.drawable.icon_great_small_active)
                    }
                }
            }
            _sharedImageUiState.emit(resultEmotionImage)
        }
    }

    private fun getEmotionDescription() {
        val result: MutableList<EmotionDescriptionTask> = mutableListOf()
        viewModelScope.launch {
            result.addAll(getEmotionDesc(getEmotionDescUC.invoke()))
            _sharedListUiState.emit(result)
        }
    }

    private fun updateDetailsState(text: String) {
        _uiState.update {
            it.copy(detailsText = text)
        }
    }

    private fun updateAnalysisState(text: String) {
        _uiState.update {
            it.copy(analysisText = text)
        }
    }

    private fun updateSensationsState(text: String) {
        _uiState.update {
            it.copy(sensationsText = text)
        }
    }

    private fun updateTypeState(text: String) {
        _uiState.update {
            it.copy(typeText = text)
        }
    }

    private fun changeVisible() {
        _uiState.update {
            it.copy(visible = !it.visible)
        }
    }
}