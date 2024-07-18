package care.intouch.app.feature.home.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.feature.home.domain.models.Status
import care.intouch.app.feature.home.domain.models.Task
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.cards.DropdownMenuItemsPlanCard
import care.intouch.uikit.ui.cards.PlanCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlanPager(
    taskList: List<Task>,
    onSwitcherChange: (Int, Int, Boolean) -> Unit,
    dropdownMenuDuplicate: (itemId: Int) -> Unit,
    dropdownMenuClear: (itemId: Int) -> Unit
) {
    val pagerState = rememberPagerState(
        pageCount = { taskList.size },
        initialPage = 0
    )

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(start = 28.dp, end = 28.dp),
        pageSpacing = 16.dp,
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxSize()
    ) { page ->
        var dropDownMenu by rememberSaveable {
            mutableStateOf(false)
        }
        val menuItems: List<DropdownMenuItemsPlanCard> = listOf()
        /*

        Drop down menu block

        listOf(DropdownMenuItemsPlanCard(
             stringResource(id = R.string.duplicate_item_menu),
             care.intouch.uikit.R.drawable.icon_duplicate
         ) {
             dropdownMenuDuplicate(
                 taskList[page].id
             )
             dropDownMenu = !dropDownMenu
         },
             DropdownMenuItemsPlanCard(
                 stringResource(id = R.string.clear_item_menu),
                 care.intouch.uikit.R.drawable.icon_small_trash
             ) {
                 dropdownMenuClear(
                     taskList[page].id
                 )
                 dropDownMenu = !dropDownMenu
             })*/


        PlanCard(
            chipText = StringVO.Plain(stringResource(id = taskList[page].status.stringId)),
            text = taskList[page].description,
            chipColors = when (taskList[page].status) {
                Status.IN_PROGRESS -> InTouchTheme.colors.textBlue
                Status.DONE -> InTouchTheme.colors.accentGreen
                else -> InTouchTheme.colors.accentBeige
            },
            chipTextColor = when (taskList[page].status) {
                Status.IN_PROGRESS -> InTouchTheme.colors.input.copy(alpha = 0.85f)
                Status.DONE -> InTouchTheme.colors.input.copy(alpha = 0.85f)
                else -> InTouchTheme.colors.textGreen.copy(alpha = 0.4f)
            },
            isSettingsClicked = dropDownMenu,
            onClickSetting = {
                dropDownMenu = !dropDownMenu
                dropdownMenuClear(
                    taskList[page].id
                )
            },
            dropdownMenuItemsList = menuItems,
            onClickToggle = {
                onSwitcherChange(
                    taskList[page].id,
                    page,
                    !taskList[page].isSharedWithDoctor
                )
            },
            toggleIsChecked = taskList[page].isSharedWithDoctor
        )
    }
}

@Composable
@Preview(showBackground = true, heightDp = 360)
fun PlanPagerPreview() {
    val taskList = listOf(
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
    )

    InTouchTheme {
        PlanPager(
            taskList = taskList,
            onSwitcherChange = { _, _, _ -> },
            dropdownMenuDuplicate = { _ -> },
            dropdownMenuClear = { _ -> }
        )
    }
}