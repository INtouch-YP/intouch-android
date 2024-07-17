package care.intouch.app.feature.diary.presentation.ui.emotionScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import care.intouch.app.feature.diary.domain.useCase.SaveEmotionDescUC
import care.intouch.app.feature.diary.domain.useCase.SaveEmotionUC
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.models.EmotionDataEvent
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.models.EmotionDesc
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.models.EmotionDescriptionTask
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.models.EmotionScreenState
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.models.EmotionTask
import care.intouch.app.feature.diary.presentation.ui.fillingOutScreen.fetchDescription
import care.intouch.uikit.R
import care.intouch.uikit.common.ImageVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EmotionViewModel @Inject constructor(
    private val saveEmotionUC: SaveEmotionUC,
    private val saveEmotionDescUC: SaveEmotionDescUC,
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        EmotionScreenState(
            emotion = listOf(),
            emotionList = listOf(),
            emotionListResult = mutableListOf(),
            emotionResult = EmotionDesc.EMPTY,
        )
    )
    val uiState = _uiState.asStateFlow()
    private val _sharedUiState = MutableSharedFlow<Boolean>()
    val sharedUiState = _sharedUiState.asSharedFlow()

    init {
        fetchEmotion()
        fetchDescriptionEmotion()
    }

    fun onEvent(event: EmotionDataEvent) {
        when (event) {
            is EmotionDataEvent.OnEmotionSwap -> {
                createResultEmotion(event.emotionDesc)
            }

            is EmotionDataEvent.OnEmotionDescriptionClicked -> {
                createResultDescriptionList(event.description)
            }

            EmotionDataEvent.OnSaveButtonClicked -> {
                saveEmotions()
                navigateBack()
            }
        }
    }

    private fun saveEmotions() {
        viewModelScope.launch {
            saveEmotionUC.invoke(uiState.value.emotionResult)
            saveEmotionDescUC.invoke(uiState.value.emotionListResult)
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _sharedUiState.emit(true)
        }
    }
    private fun createResultDescriptionList(descriptionTask: EmotionDescriptionTask) {
        if (uiState.value.emotionListResult.contains(descriptionTask)) {
            _uiState.value.emotionListResult.remove(descriptionTask)
        } else {
            _uiState.value.emotionListResult.add(descriptionTask)
        }
        _uiState.update {
            it.copy(emotionListResult = it.emotionListResult)
        }
    }

    private fun createResultEmotion(emotionDesc: EmotionDesc) {
        _uiState.update {
            it.copy(emotionResult = emotionDesc)
        }
    }

    private fun fetchEmotion() {
        val emotionList = listOf(
            EmotionTask(
                imageVO = ImageVO.Resource(R.drawable.icon_terrible),
                bigImageVO = ImageVO.Resource(R.drawable.icon_terrible_big),
                emotionDesc = EmotionDesc.TERRIBLE
            ),
            EmotionTask(
                imageVO = ImageVO.Resource(R.drawable.icon_bad),
                bigImageVO = ImageVO.Resource(R.drawable.icon_bad_big),
                emotionDesc = EmotionDesc.BAD
            ),
            EmotionTask(
                imageVO = ImageVO.Resource(R.drawable.icon_okay),
                bigImageVO = ImageVO.Resource(R.drawable.icon_okey_big),
                emotionDesc = EmotionDesc.OKAY
            ),
            EmotionTask(
                imageVO = ImageVO.Resource(R.drawable.icon_good),
                bigImageVO = ImageVO.Resource(R.drawable.icon_good_big),
                emotionDesc = EmotionDesc.GOOD
            ),
            EmotionTask(
                imageVO = ImageVO.Resource(R.drawable.icon_great),
                bigImageVO = ImageVO.Resource(R.drawable.icon_great_big),
                emotionDesc = EmotionDesc.GREAT
            ),
        )
        _uiState.update { state ->
            state.copy(emotion = emotionList)
        }
    }

    private fun fetchDescriptionEmotion() {
        val descriptionEmotionList = fetchDescription()
        _uiState.update { state ->
            state.copy(emotionList = descriptionEmotionList)
        }
    }
}