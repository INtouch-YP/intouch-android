package care.intouch.app.feature.home.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import care.intouch.app.feature.home.presentation.models.HomeUiState

@Composable
fun MyPlanCards(
    screenState: HomeUiState,
    onPlanSwitcherChange: (Int, Int, Boolean) -> Unit,
    dropdownMenuDuplicate: (itemId: Int) -> Unit,
    dropdownMenuClear: (itemId: Int) -> Unit
) {
    if (screenState.taskList.isNotEmpty()) {
        PlanPager(
            screenState.taskList,
            onSwitcherChange = onPlanSwitcherChange,
            dropdownMenuDuplicate = dropdownMenuDuplicate,
            dropdownMenuClear = dropdownMenuClear
        )
    } else {
        PlanPlaceHolder()
    }
}

@Composable
@Preview(showBackground = true, heightDp = 360)
fun MyPlanCardsPreview() {
    MyPlanCards(
        screenState = HomeUiState(),
        onPlanSwitcherChange = { _, _, _ -> },
        dropdownMenuDuplicate = { _-> },
        dropdownMenuClear = { _ -> }
    )
}

