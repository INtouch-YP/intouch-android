package care.intouch.app.feature.diary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import care.intouch.app.feature.diary.presentation.ui.DiaryLayout
import care.intouch.app.feature.diary.presentation.ui.models.DialogState
import care.intouch.app.feature.diary.presentation.ui.models.DiaryChangeEvent
import care.intouch.app.feature.diary.presentation.ui.models.DiaryEntry
import care.intouch.app.feature.diary.presentation.ui.models.DiaryPopUp
import care.intouch.app.feature.diary.presentation.ui.models.DiaryUiState
import care.intouch.app.feature.diary.presentation.ui.models.Mood
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.LoadingContainer
import care.intouch.uikit.ui.buttons.PrimaryButtonGreen
import care.intouch.uikit.ui.diary.ConfirmDeleteDialog
import care.intouch.uikit.ui.diary.DiaryHeader

@Composable
fun DiaryNoteScreen(
    onMakeNoteClick: () -> Unit,
    onBackButtonClick: () -> Unit,
) {
    val viewModel: DiaryViewModel = hiltViewModel()
    val screenState by viewModel.diaryUIState.collectAsState()
    var isDialogVisible by remember { mutableStateOf(false) }
    var dialogState by remember { mutableStateOf(DialogState()) }
    val sideEffect = viewModel.sideEffect

    LaunchedEffect(key1 = sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is DiaryPopUp.ShowPopUp -> {
                    isDialogVisible = true
                    dialogState = DialogState(
                        title = effect.title,
                        massage = effect.massage,
                        onConfirmButtonText = effect.onConfirmButtonText,
                        onDismissButtonText = effect.onDismissButtonText,
                        onConfirm = {
                            effect.onConfirm()
                            isDialogVisible = false
                        },
                        onDismiss = {
                            effect.onDismiss()
                            isDialogVisible = false
                        }
                    )
                }
            }
        }
    }
    DiaryNoteScreen(
        onMakeNoteClick = { onMakeNoteClick.invoke() },
        onBackButtonClick = { onBackButtonClick.invoke() },
        onEvent = viewModel::onEvent,
        state = screenState,
        isDialogVisible = isDialogVisible,
        dialogState = dialogState
    )
}

@Composable
private fun DiaryNoteScreen(
    onMakeNoteClick: () -> Unit,
    onBackButtonClick: () -> Unit,
    onEvent: (event: DiaryChangeEvent) -> Unit,
    state: DiaryUiState,
    isDialogVisible: Boolean = false,
    dialogState: DialogState = DialogState()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(InTouchTheme.colors.input85),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DiaryHeader(
                modifier = Modifier,
                onBackArrowClick = { onBackButtonClick.invoke() },
                title = StringVO.Resource(care.intouch.app.R.string.diary_title)
            )
            LoadingContainer(
                modifier = Modifier.fillMaxSize(),
                isLoading = state.isLoading
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 60.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PrimaryButtonGreen(
                        onClick = { onMakeNoteClick.invoke() },
                        modifier = Modifier
                            .padding(bottom = 14.dp, top = 16.dp),
                        text = StringVO.Resource(care.intouch.app.R.string.make_note_button)
                    )
                    DiaryLayout(
                        diaryEntryList = state.diaryList,
                        onDeleteButtonClicked = { itemId, itemIndex ->
                            onEvent(
                                DiaryChangeEvent.IntentionToDelete(
                                    idToDelete = itemId,
                                    index = itemIndex
                                )
                            )
                        },
                        onSwitcherChange = { idToShare, index, shareWithDoc ->
                            onEvent(
                                DiaryChangeEvent.OnShareWithDoc(
                                    idToShare = idToShare,
                                    index = index,
                                    sharedWithDoctor = shareWithDoc
                                )
                            )
                        }
                    )
                }
            }
        }
    }
    if (isDialogVisible) {
        ConfirmDeleteDialog(
            onConfirm = { dialogState.onConfirm.invoke() },
            onCancel = { dialogState.onDismiss.invoke() },
            deleteQuestion = dialogState.title,
            deleteWarning = dialogState.massage,
            confirmButtonText = dialogState.onConfirmButtonText,
            cancelButtonText = dialogState.onDismissButtonText
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DiaryNoteScreenLoadingPreview() {
    DiaryNoteScreen(
        onMakeNoteClick = { },
        onBackButtonClick = { },
        onEvent = {},
        state = DiaryUiState().copy(isLoading = true),
        isDialogVisible = false,
        dialogState = DialogState()
    )
}

@Composable
@Preview(showBackground = true)
fun DiaryNoteScreenEmpty() {
    InTouchTheme {
        DiaryNoteScreen(
            onMakeNoteClick = { },
            onBackButtonClick = { },
            onEvent = {},
            state = DiaryUiState().copy(diaryList = emptyList()),
            isDialogVisible = false,
            dialogState = DialogState()
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenWithDiaryPreview() {
    InTouchTheme {
        val screenState by remember {
            mutableStateOf(
                DiaryUiState(
                    diaryList = listOf(
                        DiaryEntry(
                            id = 1,
                            data = buildString {
                                append("13, mar")
                            },
                            note = buildString {
                                append("Lorem Ipsum dolor sit amet Lorem Ipsum Невероятно")
                                append("длинный текст, который не должен поместиться на экране,")
                                append("а в конце должны быть точески ")
                            },
                            moodList = listOf(
                                Mood(name = "Bad"),
                                Mood(name = "Loneliness"),
                                Mood(name = "Loneliness")
                            ),
                            sharedWithDoc = false
                        ),
                        DiaryEntry(
                            id = 1,
                            data = buildString {
                                append("13, jul")
                            },
                            note = buildString {
                                append("Lorem Ipsum dolor sit amet Lorem Ipsum Невероятно")
                                append("длинный текст, который не должен поместиться на экране,")
                                append("а в конце должны быть точески ")
                            },
                            moodList = listOf(Mood(name = "Bad")),
                            sharedWithDoc = false
                        ),
                        DiaryEntry(
                            id = 1,
                            data = buildString {
                                append("13, jul")
                            },
                            note = buildString {
                                append("Lorem Ipsum dolor sit amet Lorem Ipsum Невероятно")
                                append("длинный текст, который не должен поместиться на экране,")
                                append("а в конце должны быть точески ")
                            },
                            moodList = listOf(Mood(name = "Bad")),
                            sharedWithDoc = false
                        ),
                        DiaryEntry(
                            id = 1,
                            data = buildString {
                                append("13, jul")
                            },
                            note = buildString {
                                append("Lorem Ipsum dolor sit amet Lorem Ipsum Невероятно")
                                append("длинный текст, который не должен поместиться на экране,")
                                append("а в конце должны быть точески ")
                            },
                            moodList = listOf(Mood(name = "Bad")),
                            sharedWithDoc = false
                        ),
                        DiaryEntry(
                            id = 1,
                            data = buildString {
                                append("13, jul")
                            },
                            note = buildString {
                                append("Lorem Ipsum dolor sit amet Lorem Ipsum Невероятно")
                                append("длинный текст, который не должен поместиться на экране,")
                                append("а в конце должны быть точески ")
                            },
                            moodList = listOf(Mood(name = "Bad")),
                            sharedWithDoc = false
                        ),
                        DiaryEntry(
                            id = 1,
                            data = buildString {
                                append("13, jul")
                            },
                            note = buildString {
                                append("Lorem Ipsum dolor sit amet Lorem Ipsum Невероятно")
                                append("длинный текст, который не должен поместиться на экране,")
                                append("а в конце должны быть точески ")
                            },
                            moodList = listOf(Mood(name = "Bad")),
                            sharedWithDoc = false
                        )
                    )
                )
            )
        }
        DiaryNoteScreen(
            onMakeNoteClick = { },
            onBackButtonClick = { },
            onEvent = {},
            state = screenState,
            isDialogVisible = false,
            dialogState = DialogState()
        )
    }
}