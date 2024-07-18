package care.intouch.app.feature.home.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import care.intouch.app.feature.home.domain.models.DiaryEntry
import care.intouch.app.feature.home.domain.models.Mood
import care.intouch.app.feature.home.domain.models.Status
import care.intouch.app.feature.home.domain.models.Task
import care.intouch.app.feature.home.presentation.HomeViewModel
import care.intouch.app.feature.home.presentation.models.EventType
import care.intouch.app.feature.home.presentation.models.HomeScreenSideEffect
import care.intouch.app.feature.home.presentation.models.HomeUiState
import care.intouch.app.models.DialogState
import care.intouch.app.models.ToastState
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.LoadingContainer
import care.intouch.uikit.ui.customShape.CustomHeaderShape
import care.intouch.uikit.ui.events.Dialog
import care.intouch.uikit.ui.events.OneButtonDialog
import care.intouch.uikit.ui.events.Toast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import care.intouch.app.R as AppR

@Composable
fun HomeScreen(
    onSeeAllPlanClicked: () -> Unit,
    onSeeAllDiaryClicked: () -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val screenState by viewModel.homeUIState.collectAsState()
    var isDialogVisible by remember { mutableStateOf(false) }
    var isToastVisible by remember { mutableStateOf(false) }
    var dialogState by remember { mutableStateOf(DialogState()) }
    var toastState by remember { mutableStateOf(ToastState()) }
    val sideEffect = viewModel.sideEffect
    val coroutineScope = rememberCoroutineScope()
    val popUpDelayMsc = 1000L


    LaunchedEffect(key1 = sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is HomeScreenSideEffect.ShowDialog -> {
                    isDialogVisible = true
                    dialogState = DialogState(
                        header = effect.header,
                        message = effect.message,
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

                is HomeScreenSideEffect.ShowToast -> {
                    isToastVisible = true
                    toastState = ToastState(
                        massage = effect.message,
                        onDismiss = {
                            effect.onDismiss()
                            coroutineScope.launch {
                                delay(popUpDelayMsc)
                                isToastVisible = false
                            }
                        }
                    )
                }
            }
        }
    }
    LoadingContainer(
        isLoading = screenState.isLoading,
    ) {
        HomeScreen(
            state = screenState,
            onEvent = viewModel::executeEvent,
            onSeeAllPlanClicked = onSeeAllPlanClicked,
            onSeeAllDiaryClicked = onSeeAllDiaryClicked,
            isDialogVisible = isDialogVisible,
            dialogState = dialogState,
            isToastVisible = isToastVisible,
            toastState = toastState
        )
    }

}

@Composable
fun HomeScreen(
    state: HomeUiState,
    onEvent: (event: EventType) -> Unit,
    onSeeAllPlanClicked: () -> Unit,
    onSeeAllDiaryClicked: () -> Unit,
    isDialogVisible: Boolean = false,
    dialogState: DialogState = DialogState(),
    isToastVisible: Boolean = false,
    toastState: ToastState = ToastState(),
) {
    Box(
        modifier = Modifier
            .background(InTouchTheme.colors.input85)
            .draggable(
                state = rememberDraggableState { onDelta ->
                    if (onDelta > 5f)
                        onEvent(EventType.RefreshScreen)
                },
                orientation = Orientation.Vertical
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(98.dp),
                shape = CustomHeaderShape(),
                color = InTouchTheme.colors.mainBlue
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 36.dp),
                    text = stringResource(id = AppR.string.hi_title, state.userName),
                    style = InTouchTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    color = InTouchTheme.colors.textBlue
                )
            }

            HomeScreenSegment(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .padding(top = 28.dp),
                isSeeAllVisible = state.isSeeAllPlanVisible,
                titleText = stringResource(id = AppR.string.my_plan_sub_title),
                seeAllClicked = { onSeeAllPlanClicked() }
            ) {
                MyPlanCards(
                    screenState = state,
                    onPlanSwitcherChange = { id, index, switcherState ->
                        onEvent(
                            EventType.ShareTask(
                                taskId = id,
                                index = index,
                                isSharedWithDoctor = switcherState
                            )
                        )
                    },
                    dropdownMenuDuplicate = { _ ->
                    },
                    dropdownMenuClear = { taskId ->
                        onEvent(
                            EventType.ClearTask(
                                taskId = taskId
                            )
                        )
                    }
                )
            }

            HomeScreenSegment(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f)
                    .padding(top = 28.dp, bottom = 60.dp),
                isSeeAllVisible = state.isDiaryListVisible,
                titleText = stringResource(id = AppR.string.my_diary_sub_title),
                seeAllClicked = { onSeeAllDiaryClicked() }
            ) {
                MyDiaryCards(
                    screenState = state,
                    onDeleteButtonClicked = { itemId, itemIndex ->
                        onEvent(
                            EventType.DeleteDiaryEntry(
                                diaryEntryId = itemId
                            )
                        )
                    },
                    onDiarySwitchChanged = { diaryEntryId, index, switcherState ->
                        onEvent(
                            EventType.ShareDiaryEntry(
                                diaryEntryId = diaryEntryId,
                                index = index,
                                isSharedWithDoctor = switcherState
                            )
                        )
                    }
                )
            }
        }

        if (isToastVisible) {
            Toast(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, bottom = 80.dp)
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .alpha(0.8F)
                    .height(36.dp),
                text = toastState.massage.value()
            )
            toastState.onDismiss()
        }

        if (isDialogVisible) {
            if (state.isConnectionLost) {
                OneButtonDialog(
                    modifier = Modifier.fillMaxSize(),
                    dialogHeaderText = dialogState.header.value(),
                    dialogMessageText = dialogState.message.value(),
                    dialogImage = dialogState.image,
                    confirmButtonText = dialogState.onConfirmButtonText.value(),
                    onConfirmation = { dialogState.onConfirm() }
                )
            } else {
                FoldingScreen()
                Dialog(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 28.dp),
                    onDismissRequest = {
                        dialogState.onDismiss()
                    },
                    onConfirmation = { dialogState.onConfirm() },
                    dialogHeaderText = dialogState.header.value(),
                    dialogMessageText = dialogState.message.value(),
                    dismissButtonText = dialogState.onDismissButtonText.value(),
                    confirmButtonText = dialogState.onConfirmButtonText.value()
                )
            }
        }
    }
}

@Composable
fun FoldingScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.5F), color = InTouchTheme.colors.white,
        content = {}
    )
}

@Composable
@Preview(showBackground = true)
fun HomeScreenWithPlanPreview() {
    InTouchTheme {
        val screenState by remember {
            mutableStateOf(
                HomeUiState(
                    taskList = listOf(
                        Task(
                            id = 1,
                            status = Status.TO_DO,
                            isSharedWithDoctor = false,
                            description = buildString {
                                append("Невероятно длинный текст,")
                                append("который не должен поместиться на экране,")
                                append("а в конце должны быть точески")
                            }
                        ),
                        Task(
                            id = 1,
                            status = Status.TO_DO,
                            isSharedWithDoctor = false,
                            description = buildString {
                                append("Невероятно длинный текст,")
                                append("который не должен поместиться на экране,")
                                append("а в конце должны быть точески")
                            }
                        )
                    ),
                )
            )
        }
        HomeScreen(
            state = screenState,
            onEvent = { },
            onSeeAllPlanClicked = {},
            onSeeAllDiaryClicked = {})
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenEmptyPreview() {
    InTouchTheme {
        HomeScreen(
            state = HomeUiState(),
            onEvent = { },
            onSeeAllPlanClicked = {},
            onSeeAllDiaryClicked = {})
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenWithDiaryPreview() {
    InTouchTheme {
        val screenState by remember {
            mutableStateOf(
                HomeUiState(
                    diaryList = listOf(
                        DiaryEntry(
                            id = 1,
                            date = buildString {
                                append("13, jul")
                            },
                            note = buildString {
                                append("Lorem Ipsum dolor sit amet Lorem Ipsum Невероятно")
                                append("длинный текст, который не должен поместиться на экране,")
                                append("а в конце должны быть точески ")
                            },
                            moodList = listOf(
                                Mood.Loneliness,
                                Mood.Joy,
                                Mood.Hope
                            ),
                            isSharedWithDoctor = false
                        ),
                        DiaryEntry(
                            id = 1,
                            date = buildString {
                                append("13, jul")
                            },
                            note = buildString {
                                append("Lorem Ipsum dolor sit amet Lorem Ipsum Невероятно")
                                append("длинный текст, который не должен поместиться на экране,")
                                append("а в конце должны быть точески ")
                            },
                            moodList = listOf(Mood.Fear),
                            isSharedWithDoctor = false
                        )
                    )
                )
            )
        }
        HomeScreen(
            state = screenState,
            onEvent = { },
            onSeeAllPlanClicked = {},
            onSeeAllDiaryClicked = {})
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenFullPreview() {
    val screenState by remember {
        mutableStateOf(
            HomeUiState(
                taskList = mutableStateListOf(
                    Task(
                        id = 1,
                        status = Status.IN_PROGRESS,
                        isSharedWithDoctor = false,
                        description = buildString {
                            append("Невероятно длинный текст,")
                            append("который не должен поместиться на экране,")
                            append("а в конце должны быть точески")
                        }
                    ),
                    Task(
                        id = 1,
                        status = Status.IN_PROGRESS,
                        isSharedWithDoctor = false,
                        description = buildString {
                            append("Невероятно длинный текст,")
                            append("который не должен поместиться на экране,")
                            append("а в конце должны быть точески")
                        }
                    ),
                    Task(
                        id = 1,
                        status = Status.IN_PROGRESS,
                        isSharedWithDoctor = false,
                        description = buildString {
                            append("Невероятно длинный текст,")
                            append("который не должен поместиться на экране,")
                            append("а в конце должны быть точески")
                        }
                    )
                ),
                diaryList = mutableStateListOf(
                    DiaryEntry(
                        id = 1,
                        date = buildString {
                            append("13, jul")
                        },
                        note = buildString {
                            append("Lorem Ipsum dolor sit amet Lorem Ipsum Невероятно")
                            append("длинный текст, который не должен поместиться на экране,")
                            append("а в конце должны быть точески ")
                        },
                        moodList = listOf(
                            Mood.Loneliness,
                            Mood.Joy,
                            Mood.Hope
                        ),
                        isSharedWithDoctor = false
                    ),
                    DiaryEntry(
                        id = 1,
                        date = buildString {
                            append("13, jul")
                        },
                        note = buildString {
                            append("Lorem Ipsum dolor sit amet Lorem Ipsum Невероятно")
                            append("длинный текст, который не должен поместиться на экране,")
                            append("а в конце должны быть точески ")
                        },
                        moodList = listOf(
                            Mood.Loneliness,
                            Mood.Joy,
                            Mood.Hope
                        ),
                        isSharedWithDoctor = false
                    )
                )
            )
        )
    }

    InTouchTheme {
        HomeScreen(
            state = screenState,
            onEvent = { },
            onSeeAllPlanClicked = {},
            onSeeAllDiaryClicked = {})
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenLoadingPreview() {
    InTouchTheme {
        LoadingContainer(
            isLoading = true,
        ) {
            HomeScreen(
                state = HomeUiState().copy(isLoading = true),
                onEvent = { },
                onSeeAllPlanClicked = {},
                onSeeAllDiaryClicked = {})
        }
    }
}
