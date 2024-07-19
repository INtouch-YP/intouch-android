package care.intouch.app.ui.uiKitSamples.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.R
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.cards.CardLine
import care.intouch.uikit.ui.cards.DropdownMenuItemsPlanCard
import care.intouch.uikit.ui.cards.NoteCards
import care.intouch.uikit.ui.cards.PlanCard

@Preview(showBackground = true)
@Composable
fun CardsSample() {
    Column(
        modifier = Modifier.background(InTouchTheme.colors.input)
    ) {
        var isSelectedNoteCard by remember { mutableStateOf(false) }
        var isSelectedNoteCardSec by remember { mutableStateOf(false) }
        var isSelectedPlanCard by remember { mutableStateOf(false) }
        var onClickSetting by remember { mutableStateOf(false) }

        val menuItems: List<DropdownMenuItemsPlanCard> =
            listOf(DropdownMenuItemsPlanCard("Duplicate", R.drawable.icon_duplicate) {},
                DropdownMenuItemsPlanCard("Clear", R.drawable.icon_small_trash) {})

        Column(modifier = Modifier.fillMaxSize()) {
            NoteCards(dateText = "13 jul",
                noteText = "Lorem Ipsum dolor sit amet Lorem Ipsum... ",
                moodChipsList = listOf(StringVO.Plain("Bad"), StringVO.Plain("Loneliness")),
                toggleIsChecked = isSelectedNoteCard,
                onClickTrash = { }) { isSelectedNoteCard = !isSelectedNoteCard }
            Spacer(modifier = Modifier.height(8.dp))

            NoteCards(dateText = "13 jul",
                noteText = "Lorem Ipsum dolor ",
                moodChipsList = listOf(StringVO.Plain("Bad"), StringVO.Plain("Loneliness")),
                toggleIsChecked = isSelectedNoteCardSec,
                onClickTrash = { }) { isSelectedNoteCardSec = !isSelectedNoteCardSec }
            Spacer(modifier = Modifier.height(8.dp))

            PlanCard(
                chipText = StringVO.Plain("Done"),
                text = "Socratic dialogue Learning...\n" + "Lorem ipsum dolor sit amet ",
                toggleIsChecked = isSelectedPlanCard,
                onClickSetting = { onClickSetting = it },
                chipColors = InTouchTheme.colors.mainGreen,
                chipTextColor = InTouchTheme.colors.input,
                dateText = "May, 15  2023",
                dropdownMenuItemsList = menuItems,
                isSettingsClicked = onClickSetting,
                onClickToggle = {
                    isSelectedPlanCard = !isSelectedPlanCard
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            CardLine(
                dateText = "May, 15  2023",
                chipText = StringVO.Plain("In progress"),
                chipTextColor = InTouchTheme.colors.input.copy(alpha = .85f),
                chipColors = InTouchTheme.colors.textBlue
            ) {}
        }
    }
}