package care.intouch.app.feature.home.presentation.ui

import androidx.compose.foundation.background
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import care.intouch.app.feature.home.presentation.HomeViewModel
import care.intouch.app.feature.home.presentation.models.DiaryEntry
import care.intouch.app.feature.home.presentation.models.EventType
import care.intouch.app.feature.home.presentation.models.HomeScreenSideEffect
import care.intouch.app.feature.home.presentation.models.HomeUiState
import care.intouch.app.feature.home.presentation.models.Mood
import care.intouch.app.feature.home.presentation.models.Status
import care.intouch.app.feature.home.presentation.models.Task
import care.intouch.app.models.DialogState
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.LoadingContainer
import care.intouch.uikit.ui.cards.ConformationDialog
import care.intouch.uikit.ui.customShape.CustomHeaderShape
import care.intouch.app.R as AppR

@Composable
fun HomeScreen(
    onSeeAllPlanClicked: () -> Unit,
    onSeeAllDiaryClicked: () -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val screenState by viewModel.homeUIState.collectAsState()
    var isDialogVisible by remember { mutableStateOf(false) }
    var dialogState by remember { mutableStateOf(DialogState()) }
    val sideEffect = viewModel.sideEffect

    LaunchedEffect(key1 = sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is HomeScreenSideEffect.ShowDialog -> {
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
                            effect.onConfirm()
                            isDialogVisible = false
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
            dialogState = dialogState
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
    dialogState: DialogState = DialogState()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(InTouchTheme.colors.white),
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
                        .padding(top = 24.dp),
                    text = stringResource(id = AppR.string.hi_title, "Bob"),
                    style = InTouchTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    color = InTouchTheme.colors.textBlue
                )
            }

            HomeScreenSegment(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.55f)
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
                    dropdownMenuDuplicate = { taskId, taskIndex ->
                        onEvent(
                            EventType.DuplicateTask(
                                taskId = taskId,
                                index = taskIndex
                            )
                        )
                    },
                    dropdownMenuClear = { taskId, taskIndex ->
                        onEvent(
                            EventType.ClearTask(
                                taskId = taskId,
                                index = taskIndex
                            )
                        )
                    }
                )
            }

            HomeScreenSegment(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f)
                    .padding(top = 28.dp),
                isSeeAllVisible = state.isDiaryListVisible,
                titleText = stringResource(id = AppR.string.my_diary_sub_title),
                seeAllClicked = { onSeeAllDiaryClicked() }
            ) {
                MyDiaryCards(
                    screenState = state,
                    onDeleteButtonClicked = { itemId, itemIndex ->
                        onEvent(
                            EventType.DeleteDiaryEntry(
                                diaryEntryId = itemId,
                                index = itemIndex
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

        if (isDialogVisible) {
            FoldingScreen()
            ConformationDialog(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 28.dp),
                onDismissRequest = {
                    dialogState.onDismiss()
                },
                onConfirmation = { dialogState.onConfirm() },
                headerText = dialogState.title.value(),
                dialogText = dialogState.massage.value(),
                dismissButtonText = dialogState.onDismissButtonText.value(),
                confirmButtonText = dialogState.onConfirmButtonText.value()
            )
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
                            data = buildString {
                                append("13, jul")
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
                            isSharedWithDoctor = false
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
                        data = buildString {
                            append("13, jul")
                        },
                        note = buildString {
                            append("Lorem Ipsum dolor sit amet Lorem Ipsum Невероятно")
                            append("длинный текст, который не должен поместиться на экране,")
                            append("а в конце должны быть точески ")
                        },
                        moodList = listOf(
                            Mood(
                                name = buildString {
                                    append("Bad")
                                }
                            ),
                            Mood(
                                name = buildString {
                                    append("Loneliness")
                                }
                            ),
                            Mood(
                                name = buildString {
                                    append("Loneliness")
                                }
                            )
                        ),
                        isSharedWithDoctor = false
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
                        moodList = listOf(
                            Mood(
                                name = buildString {
                                    append("Bad")
                                }
                            )
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
