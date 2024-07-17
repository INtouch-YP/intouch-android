package care.intouch.app.feature.diary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import care.intouch.app.R
import care.intouch.app.feature.diary.presentation.ui.CustomChipGroup
import care.intouch.app.feature.diary.presentation.ui.EmotionPicker
import care.intouch.app.feature.diary.presentation.ui.models.Mood
import care.intouch.app.feature.diary.presentation.ui.models.newnote.Emotion
import care.intouch.app.feature.diary.presentation.ui.models.newnote.NewNoteDataState
import care.intouch.app.feature.diary.presentation.ui.models.newnote.NewNoteEvent
import care.intouch.app.feature.diary.presentation.ui.models.newnote.NewNoteUIState
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.LoadingContainer
import care.intouch.uikit.ui.buttons.PrimaryButtonGreen
import care.intouch.uikit.ui.navigation.CustomTopBar
import care.intouch.uikit.ui.textFields.MultilineTextField
import care.intouch.uikit.ui.toggle.Toggle

@Composable
fun DiaryEntriesScreen(
    onNextClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    val viewModel: NewNoteViewModel = hiltViewModel()
    val screenState by viewModel.newNoteUIState.collectAsState()
    val dataState by viewModel.newNoteDataState.collectAsState()

    DiaryEntriesScreen(
        onNextClick = { onNextClick.invoke() },
        onCloseClick = { onCloseClick.invoke() },
        onEvent = viewModel::onEvent,
        stateScreen = screenState,
        stateData = dataState
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DiaryEntriesScreen(
    onNextClick: () -> Unit,
    onCloseClick: () -> Unit,
    onEvent: (event: NewNoteEvent) -> Unit,
    stateScreen: NewNoteUIState,
    stateData: NewNoteDataState
) {

    Surface(modifier = Modifier.fillMaxSize(), color = InTouchTheme.colors.mainBlue) {}
    if (stateScreen.isLoading) {
        LoadingContainer(
            modifier = Modifier.fillMaxSize(),
            isLoading = true
        ) {}
    } else {
        LazyColumn(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                CustomTopBar(
                    onBackArrowClick = { },
                    onCloseButtonClick = { onCloseClick.invoke() },
                    enabledArcButton = false,
                    addBackArrowButton = false,
                    addCloseButton = true
                )
            }
            item {
                MultilineTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 28.dp, end = 28.dp, top = 28.dp),
                    titleText = StringVO.Resource(R.string.event_sub_title_emotional),
                    subtitleText = StringVO.Resource(R.string.event_desc_emotional),
                    value = stateData.eventDetails,
                    onValueChange = {
                        onEvent(NewNoteEvent.EventDetailsTextChange(it))
                    },
                    isError = false,
                    enabled = true,
                    hint = StringVO.Resource(R.string.edit_text_hint)
                )
            }
            item {
                MultilineTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 28.dp, end = 28.dp, top = 40.dp),
                    titleText = StringVO.Resource(R.string.thoughts_sub_title_emotional),
                    subtitleText = StringVO.Resource(R.string.thoughts_desc_emotional),
                    value = stateData.thoughtsAnalysis,
                    onValueChange = {
                        onEvent(NewNoteEvent.ThoughtsAnalysisTextChange(it))
                    },
                    isError = false,
                    enabled = true,
                    hint = StringVO.Resource(R.string.edit_text_hint)
                )
            }
            item {
                MultilineTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 28.dp, end = 28.dp, top = 40.dp),
                    titleText = StringVO.Resource(R.string.emotional_type_sub_title_emotional),
                    subtitleText = StringVO.Resource(R.string.emotional_type_desc_emotional),
                    value = stateData.emotionType,
                    onValueChange = {
                        onEvent(NewNoteEvent.EmotionalTypeTextChange(it))
                    },
                    isError = false,
                    enabled = true,
                    hint = StringVO.Resource(R.string.edit_text_hint)
                )
            }
            item {
                EmotionPicker(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .padding(start = 28.dp, end = 28.dp, top = 8.dp),
                    buttonText = StringVO.Resource(R.string.add_emotions_button),
                    selectedEmotion = stateData.primaryEmotion,
                    onAddClick = {
                        onEvent(
                            NewNoteEvent.EmotionsChange(
                                Emotion.GREAT,
                                listOf(Mood(name = "Sadness"))
                            )
                        )
                    },
                    onEditClick = { onNextClick.invoke() },
                    onValueChange = {},
                    onTrashClick = { onEvent(NewNoteEvent.OnClearClick) }
                )
            }
            item {
                if (!stateData.clarifyEmotions.isEmpty())
                    CustomChipGroup(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 6.dp, start = 28.dp, end = 28.dp),
                        chips = stateData.clarifyEmotions
                    ) {}
            }

            item {
                MultilineTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 28.dp, end = 28.dp, top = 40.dp),
                    titleText = StringVO.Resource(R.string.sensations_sub_title_emotional),
                    subtitleText = StringVO.Resource(R.string.sensations_desc_emotional),
                    value = stateData.physicalSensations,
                    onValueChange = {
                        onEvent(NewNoteEvent.PhysicalSensationsTextChange(it))
                    },
                    isError = false,
                    enabled = true,
                    hint = StringVO.Resource(R.string.edit_text_hint)
                )
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 28.dp, end = 28.dp, top = 16.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(top = 5.dp),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.share_with_therapist),
                        style = InTouchTheme.typography.bodySemibold,

                        )
                    Spacer(modifier = Modifier.weight(1f))
                    Toggle(
                        modifier = Modifier.padding(start = 8.dp),
                        isChecked = stateData.shareWithDoc,
                        onChange = { check ->
                            onEvent(
                                NewNoteEvent.OnShareWithDoc(
                                    stateData.id,
                                    stateData.shareWithDoc
                                )
                            )
                        }
                    )
                }
            }
            item {
                PrimaryButtonGreen(
                    modifier = Modifier.padding(top = 40.dp),
                    onClick = {
                        onEvent(
                            NewNoteEvent.SaveNote(
                                id = stateData.id,
                                emotionType = stateData.emotionType,
                                eventDetails = stateData.eventDetails,
                                physicalSensations = stateData.physicalSensations,
                                thoughtsAnalysis = stateData.thoughtsAnalysis,
                                shareWithDoc = stateData.shareWithDoc,
                                primaryEmotion = stateScreen.primaryEmotion,
                                clarifyEmotions = stateScreen.clarifyEmotions,

                                )
                        )
                        onCloseClick.invoke()
                    },
                    isEnabled = true,
                    text = StringVO.Resource(R.string.save_button)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, heightDp = 1300)
fun DiaryEntriesScreenPreview() {
    InTouchTheme {
        DiaryEntriesScreen(
            onNextClick = {},
            onCloseClick = {},
            onEvent = {},
            stateScreen = NewNoteUIState(),
            stateData = NewNoteDataState()
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DiaryEntriesScreenPreviewLoading() {
    InTouchTheme {
        DiaryEntriesScreen(
            onNextClick = {},
            onCloseClick = {},
            onEvent = {},
            stateScreen = NewNoteUIState().copy(isLoading = true),
            stateData = NewNoteDataState()
        )
    }
}

@Composable
@Preview(showBackground = true, heightDp = 1400)
fun DiaryEntriesScreenFilledPreview() {
    InTouchTheme {
        val text = buildString {
            append("Bobr\n")
            append("dobr")
        }
        val prEmo = Emotion.GREAT
        val moodList = listOf(
            Mood(name = "Sadness"),
            Mood(name = "Happiness"),
            Mood(name = "Anger"),
            Mood(name = "Fear"),
            Mood(name = "Surprise")
        )
        val dataStateP by remember {
            mutableStateOf(
                NewNoteDataState(
                    id = 123,
                    eventDetails = text,
                    thoughtsAnalysis = text,
                    physicalSensations = text,
                    emotionType = text,
                    primaryEmotion = prEmo,
                    clarifyEmotions = moodList,
                    shareWithDoc = true
                )
            )
        }
        DiaryEntriesScreen(
            onNextClick = {},
            onCloseClick = {},
            onEvent = {},
            stateScreen = NewNoteUIState().copy(),
            stateData = dataStateP
        )
    }
}
