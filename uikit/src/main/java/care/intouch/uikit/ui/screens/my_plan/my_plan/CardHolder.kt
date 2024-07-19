package care.intouch.uikit.ui.screens.my_plan.my_plan

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.R
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.cards.DropdownMenuItemsPlanCard
import care.intouch.uikit.ui.cards.PlanCard

@Composable
fun CardHolder(
    modifier: Modifier = Modifier,
    chipText: StringVO,
    text: String,
    backgroundColor: Color = InTouchTheme.colors.mainBlue,
    dateText: String,
    onCardHolderClick: () -> Unit,
    onDuplicateMenuItemClick: () -> Unit,
    onClearMenuItemClick: () -> Unit,
    onClickToggle: (Boolean) -> Unit,
) {
    var onClickSetting by remember { mutableStateOf(false) }

    var toggleIsChecked by remember {
        mutableStateOf(false)
    }

    val chipColor = when(chipText) {
        StringVO.Resource(resId = R.string.to_do) -> InTouchTheme.colors.accentYellow
        StringVO.Resource(resId = R.string.in_progress) -> InTouchTheme.colors.textBlue
        StringVO.Resource(resId = R.string.done) -> InTouchTheme.colors.darkGreen
        else -> backgroundColor
    }

    val chipTextColor = when(chipText.value()) {
        StringVO.Resource(resId = R.string.to_do).value() -> InTouchTheme.colors.textGreen40
        else -> InTouchTheme.colors.input
    }

    val menuItems: List<DropdownMenuItemsPlanCard> =
        listOf(DropdownMenuItemsPlanCard("Duplicate", R.drawable.icon_duplicate) {
            onDuplicateMenuItemClick.invoke()
            onClickSetting = !onClickSetting
        },
            DropdownMenuItemsPlanCard("Clear", R.drawable.icon_small_trash) {
                onClearMenuItemClick.invoke()
                onClickSetting = !onClickSetting
            })

    PlanCard(
        modifier = modifier.height(284.dp),
        chipText = chipText,
        chipColors = chipColor,
        chipTextColor = chipTextColor ,
        backgroundColor = backgroundColor,
        text = text,
        dateText = dateText,
        isSettingsClicked = onClickSetting,
        toggleIsChecked = toggleIsChecked,
        onClickSetting = { onClickSetting = it },
        dropdownMenuItemsList = menuItems,
        onClickToggle = {
            toggleIsChecked = !toggleIsChecked
            onClickToggle.invoke(toggleIsChecked)
        },
        onPlanCardClick = { onCardHolderClick.invoke() }
    )
}

@Composable
@Preview(showBackground = true)
fun CardHolderPreview() {
    CardHolder(
        chipText = StringVO.Plain("Done"),
        text = "Some text",
        dateText = "04.07.2024",
        onCardHolderClick = {},
        onDuplicateMenuItemClick = {},
        onClearMenuItemClick = {},
        onClickToggle = {}
    )
}