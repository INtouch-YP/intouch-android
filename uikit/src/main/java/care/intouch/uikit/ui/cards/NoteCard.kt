package care.intouch.uikit.ui.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.R
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.EmotionalChipsSmall
import care.intouch.uikit.ui.toggle.Toggle

@Composable
fun NoteCards(
    modifier: Modifier = Modifier,
    dateText: String,
    dateTextDivider: String = " ",
    dateColor: Color = InTouchTheme.colors.textBlue,
    dateTextStyle: TextStyle = InTouchTheme.typography.titleMedium,
    noteText: String,
    noteColor: Color = InTouchTheme.colors.textBlue,
    noteTextStyle: TextStyle = InTouchTheme.typography.bodyRegular,
    moodChipsList: List<StringVO>,
    moodChipsTextColor: Color = InTouchTheme.colors.textGreen,
    moodChipsBackgroundColor: Color = InTouchTheme.colors.accentBeige,
    toggleIsChecked: Boolean,
    backgroundColor: Color = InTouchTheme.colors.mainBlue,
    onClickTrash: () -> Unit,
    onClickToggle: (Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.padding(start = 12.dp, bottom = 20.dp, top = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = dateText.substringBefore(dateTextDivider),
                color = dateColor,
                style = dateTextStyle
            )
            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = dateText.substringAfter(dateTextDivider),
                color = dateColor,
                style = dateTextStyle
            )
        }
        Column(
            modifier = Modifier.padding(bottom = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextContainer(
                noteText,
                noteColor,
                noteTextStyle, onClickTrash,
            )
            ChipsToggleContainer(
                moodChipsList,
                moodChipsTextColor,
                moodChipsBackgroundColor,
                toggleIsChecked,
                onClickToggle
            )
        }
    }
}

@Composable
fun TextContainer(
    noteText: String,
    noteColor: Color = InTouchTheme.colors.textBlue,
    noteTextStyle: TextStyle = InTouchTheme.typography.bodyRegular,
    onClickTrash: () -> Unit,
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(start = 10.dp, end = 12.dp, top = 12.dp),
    ) {
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(R.string.note),
            style = InTouchTheme.typography.subTitle,
            color = InTouchTheme.colors.textGreen
        )
        Text(
            modifier = Modifier
                .padding(top = 10.dp, start = 16.dp)
                .weight(1f),
            maxLines = 2,
            text = noteText,
            overflow = TextOverflow.Ellipsis,
            style = noteTextStyle,
            color = noteColor,
        )
        Image(
            modifier = Modifier
                .padding(start = 16.dp)
                .size(width = 32.dp, height = 29.dp)
                .clickable { onClickTrash() },
            painter = painterResource(id = R.drawable.icon_trash_card),
            contentDescription = "Trash"
        )
    }
}

@Composable
fun ChipsToggleContainer(
    moodChipsList: List<StringVO>,
    moodChipsTextColor: Color = InTouchTheme.colors.textGreen,
    moodChipsBackgroundColor: Color = InTouchTheme.colors.accentBeige,
    toggleIsChecked: Boolean,
    onClickToggle: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(start = 10.dp, end = 12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .weight(1f)
                .padding(top = 6.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(end = 8.dp, bottom = 6.dp),
                text = stringResource(R.string.mood),
                color = InTouchTheme.colors.textGreen,
                style = InTouchTheme.typography.subTitle,
            )
            Row(verticalAlignment = Alignment.Bottom) {
                for (mood in moodChipsList.take(2)) {
                    EmotionalChipsSmall(
                        modifier = Modifier.padding(horizontal = 2.dp),
                        text = mood,
                        selected = true,
                        selectedColorText = moodChipsTextColor,
                        selectedColor = moodChipsBackgroundColor
                    ) {
                    }
                }
                if (moodChipsList.size > 2) {
                    Image(
                        modifier = Modifier
                            .padding(start = 1.dp),
                        painter = painterResource(id = R.drawable.icon_elipsis_horizontal),
                        contentDescription = "Ellipsis"
                    )
                }
            }
        }

        Toggle(
            modifier = Modifier.padding(top = 2.dp), isChecked = toggleIsChecked
        ) {
            onClickToggle(it)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewNoteCards() {
    InTouchTheme {
        NoteCards(
            dateText = "13 jul",
            noteText = "Lorem Ipsum dolor sit amet Lorem Ipsum...",
            moodChipsList = listOf(
                StringVO.Plain("Bad"), StringVO.Plain("Loneliness"), StringVO.Plain("Loneliness")
            ),
            toggleIsChecked = false,
            onClickToggle = {},
            onClickTrash = {},
            modifier = Modifier.size(
                width = 334.dp, height = 109.dp
            )
        )
    }
}