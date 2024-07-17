package care.intouch.app.feature.diary.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.feature.diary.presentation.ui.models.DiaryEntry
import care.intouch.app.feature.diary.presentation.ui.models.Mood
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.cards.NoteCards
import care.intouch.uikit.ui.diary.EmptyDiaryPlaceHolder

@Composable
fun DiaryLayout(
    modifier: Modifier = Modifier,
    diaryEntryList: List<DiaryEntry>,
    onDeleteButtonClicked: (itemId: Int, itemIndex: Int) -> Unit,
    onSwitcherChange: (Int, Int, Boolean) -> Unit
) {
    if (diaryEntryList.isEmpty()) {
        EmptyDiaryPlaceHolder(
            title = StringVO.Resource(care.intouch.app.R.string.my_diary_sub_title),
            text = StringVO.Resource(care.intouch.app.R.string.empty_diary)
        )
    } else {
        LazyColumn(
            modifier = modifier
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(diaryEntryList) { index, diaryEntry ->
                var toggleState by rememberSaveable {
                    mutableStateOf(diaryEntry.sharedWithDoc)
                }
                NoteCards(
                    modifier = Modifier.padding(top = 16.dp),
                    dateText = diaryEntry.data,
                    noteText = diaryEntry.note,
                    moodChipsList = diaryEntry.moodList.map { StringVO.Plain(it.name) },
                    toggleIsChecked = toggleState,
                    onClickToggle = {
                        toggleState = !toggleState
                        onSwitcherChange(diaryEntry.id, index, toggleState)
                    },
                    onClickTrash = { onDeleteButtonClicked(diaryEntry.id, index) })
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    heightDp = 360
)
fun DiaryLayoutPreview() {
    val diaryEntryList = listOf(
        DiaryEntry(
            id = 1,
            data = buildString {
                append("13, jul")
            },
            note = buildString {
                append("Lorem Ipsum dolor sit amet Lorem Ipsum... ")
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
            sharedWithDoc = false
        ),
        DiaryEntry(
            id = 1,
            data = buildString {
                append("13, jul")
            },
            note = buildString {
                append("Lorem Ipsum dolor sit amet Lorem Ipsum... ")
            },
            moodList = listOf(
                Mood(
                    name = buildString {
                        append("Bad")
                    }
                )
            ),
            sharedWithDoc = false
        )
    )
    var onSwitcherChange by rememberSaveable {
        mutableStateOf(false)
    }
    InTouchTheme {
        DiaryLayout(
            diaryEntryList = diaryEntryList,
            onDeleteButtonClicked = { _, _ -> },
            onSwitcherChange = { _, _, _ -> onSwitcherChange = !onSwitcherChange }
        )
    }
}

@Composable
@Preview(
    showBackground = true,
    heightDp = 360
)
fun DiaryLayoutEmptyPreview() {
    var onSwitcherChange by rememberSaveable {
        mutableStateOf(false)
    }
    InTouchTheme {
        DiaryLayout(
            diaryEntryList = emptyList(),
            onDeleteButtonClicked = { _, _ -> },
            onSwitcherChange = { _, _, _ -> onSwitcherChange = !onSwitcherChange }
        )
    }
}