package care.intouch.uikit.ui.screens.my_plan.my_plan

import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import care.intouch.uikit.ui.RegularChips

@Composable
fun ChipsRow(
    modifier: Modifier = Modifier,
    chipsRowList: List<ChipsRowItem>
) {
    var selectedChips by remember {
        mutableStateOf(chipsRowList[0].title)
    }

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
         items(chipsRowList) { chipsRowItem ->
             RegularChips(
                 text = chipsRowItem.title,
                 selected = chipsRowItem.title == selectedChips,
                 selectedColor = InTouchTheme.colors.mainGreen,
                 onClick = {
                     if (chipsRowItem.title != selectedChips) {
                         selectedChips = chipsRowItem.title
                         chipsRowItem.onItemClick.invoke()
                     }
                 }
             )
         }
    }
}

@Composable
@Preview(showBackground = true)
fun ChipsRowPreview() {

    val chipsRowList = listOf(
        ChipsRowItem(
            title = StringVO.Resource(R.string.show_all),
            onItemClick = {}
        ),
        ChipsRowItem(
            title = StringVO.Resource(R.string.to_do),
            onItemClick = {}
        ),
        ChipsRowItem(
            title = StringVO.Resource(R.string.in_progress),
            onItemClick = {}
        ),
        ChipsRowItem(
            title = StringVO.Resource(R.string.done),
            onItemClick = {}
        ),
    )

    ChipsRow(
        chipsRowList = chipsRowList
    )
}