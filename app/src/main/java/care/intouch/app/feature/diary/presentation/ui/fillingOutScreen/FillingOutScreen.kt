package care.intouch.app.feature.diary.presentation.ui.fillingOutScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import care.intouch.app.R
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.ResultEmotionDescriptionList
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.models.EmotionDescriptionTask
import care.intouch.app.feature.diary.presentation.ui.fillingOutScreen.models.FillingOutDataEvent
import care.intouch.app.feature.diary.presentation.ui.fillingOutScreen.models.FillingOutScreenState
import care.intouch.app.feature.diary.presentation.ui.fillingOutScreen.viewModel.FillingOutViewModel
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.PrimaryButtonGreen
import care.intouch.uikit.ui.cards.AddEmotionCard
import care.intouch.uikit.ui.cards.ResultEmotionCard
import care.intouch.uikit.ui.navigation.TopBarArcButton
import care.intouch.uikit.ui.textFields.MultilineTextField
import care.intouch.uikit.ui.toggle.Toggle

@Composable
fun FillingOutScreen(
    onNextClick: () -> Unit,
    viewModel: FillingOutViewModel,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val imageState by viewModel.sharedImageUiState.collectAsStateWithLifecycle(null)
    val listState by viewModel.sharedListUiState.collectAsStateWithLifecycle(listOf())
    val navigateState by viewModel.sharedNavigateState.collectAsStateWithLifecycle(false)
    FillingOutScreen(
        onNextClick = onNextClick,
        state = state,
        onEvent = { viewModel.onEvent(it) },
        onBackClick = onBackClick,
        imageState = imageState,
        listState = listState,
        navigateState = navigateState,
        onSaveClick = onSaveClick
    )
}
@Composable
fun FillingOutScreen(
    onNextClick: () -> Unit,
    state: FillingOutScreenState,
    onEvent: (FillingOutDataEvent) -> Unit,
    onBackClick: () -> Unit,
    imageState: ImageVO?,
    listState: List<EmotionDescriptionTask>,
    navigateState: Boolean,
    onSaveClick: () -> Unit
) {
    var isVisible by rememberSaveable {
        mutableStateOf(false)
    }
    Column(
        Modifier.background(InTouchTheme.colors.mainBlue)
    ) {
        LaunchedEffect(key1 = imageState) {
            onEvent(FillingOutDataEvent.OnUpdateStateChanged)
        }
        LaunchedEffect(key1 = navigateState) {
            if (navigateState)
                onNextClick.invoke()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopBarArcButton(
                onClick = onBackClick,
                enabled = true,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.height(28.dp))
            MultilineTextField(
                titleText = StringVO.Resource(R.string.event_sub_title_emotional),
                subtitleText = StringVO.Resource(R.string.event_desc_emotional),
                value = state.detailsText,
                onValueChange = { onEvent(FillingOutDataEvent.OnDetailsTextChanged(it)) },
                isError = false,
                enabled = true,
                hint = StringVO.Resource(R.string.edit_text_hint),
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.height(40.dp))
            MultilineTextField(
                titleText = StringVO.Resource(R.string.thoughts_sub_title_emotional),
                subtitleText = StringVO.Resource(R.string.thoughts_desc_emotional),
                value = state.analysisText,
                onValueChange = { onEvent(FillingOutDataEvent.OnAnalysisTextChanged(it)) },
                isError = false,
                enabled = true,
                hint = StringVO.Resource(R.string.edit_text_hint),
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.height(40.dp))
            MultilineTextField(
                titleText = StringVO.Resource(R.string.emotional_type_sub_title_emotional),
                subtitleText = StringVO.Resource(R.string.emotional_type_desc_emotional),
                value = state.typeText,
                onValueChange = { onEvent(FillingOutDataEvent.OnTypeTextChanged(it)) },
                isError = false,
                enabled = true,
                hint = StringVO.Resource(R.string.edit_text_hint),
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (imageState != null) {
                ResultEmotionCard(
                    onEditClick = { onEvent(FillingOutDataEvent.OnAddEmotionClicked) },
                    onTrashClick = { onEvent(FillingOutDataEvent.OnTrashClicked) },
                    emotionIcon = imageState
                )
                ResultEmotionDescriptionList(items = listState)
            } else {
                AddEmotionCard(
                    onClick = { onEvent(FillingOutDataEvent.OnAddEmotionClicked) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            MultilineTextField(
                titleText = StringVO.Resource(R.string.sensations_sub_title_emotional),
                subtitleText = StringVO.Resource(R.string.sensations_desc_emotional),
                value = state.sensationsText,
                onValueChange = { onEvent(FillingOutDataEvent.OnSensationsTextChanged(it)) },
                isError = false,
                enabled = true,
                hint = StringVO.Resource(R.string.edit_text_hint),
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = StringVO.Resource(R.string.share_with_therapist).value(),
                    style = InTouchTheme.typography.subTitle,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
                Toggle(
                    isChecked = isVisible,
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    onEvent(FillingOutDataEvent.OnChangeVisible)
                    isVisible = !isVisible
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            PrimaryButtonGreen(
                onClick = {
                    onEvent(FillingOutDataEvent.OnClickSave)
                    onSaveClick()
                },
                modifier = Modifier,
                text = StringVO.Resource(R.string.save_button)
            )
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
@Preview(showBackground = true, heightDp = 1350)
fun FillingOutScreenPreview() {
    val state = FillingOutScreenState(
        detailsText = "",
        analysisText = "",
        typeText = "",
        sensationsText = "",
    )
    InTouchTheme {
        FillingOutScreen(
            onNextClick = {},
            state,
            onEvent = {},
            onBackClick = {},
            imageState = null,
            listState = listOf(),
            navigateState = false,
            {}
        )
    }
}