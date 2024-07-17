package care.intouch.app.feature.diary.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.feature.diary.presentation.ui.models.Mood
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.EmotionalChips

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CustomChipGroup(modifier: Modifier, chips: List<Mood>, onChipClick: (String) -> Unit) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        maxItemsInEachRow = Int.MAX_VALUE
    ) {
        chips.forEach { chipText ->
            EmotionalChips(
                text = StringVO.Plain(chipText.name),
                onClick = { onChipClick(chipText.name) },
                modifier = Modifier,
                selected = false
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

    }
}

@Composable
@Preview(showBackground = true)
fun CustomChipGroupPreview() {
    InTouchTheme {
        val chip = List<Mood>(5) { Mood("Lost") }
        CustomChipGroup(modifier = Modifier.fillMaxWidth(), chips = chip) {

        }
    }
}