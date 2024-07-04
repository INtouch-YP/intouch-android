package care.intouch.app.feature.home.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import care.intouch.app.feature.home.presentation.models.HomeUiState

@Composable
fun MyDiaryCards(
    screenState: HomeUiState,
    onDiarySwitchChanged: (Int, Int, Boolean) -> Unit,
    onDeleteButtonClicked: (itemId: Int, itemIndex: Int) -> Unit
) {
    if (screenState.diaryList.isNotEmpty()) {
        DiaryLayout(
            screenState.diaryList, onSwitcherChange = onDiarySwitchChanged,
            onDeleteButtonClicked = onDeleteButtonClicked
        )
    } else {
        DiaryPlaceHolder()
    }
}

@Composable
@Preview(showBackground = true, heightDp = 180)
fun MyDiaryCardsPreview() {
    MyDiaryCards(
        screenState = HomeUiState(),
        onDiarySwitchChanged = { _, _, _ -> },
        onDeleteButtonClicked = { _, _ -> }
    )
}
