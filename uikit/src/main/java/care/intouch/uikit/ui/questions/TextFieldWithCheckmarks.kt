package care.intouch.uikit.ui.questions

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.checkmark.CheckmarkWithText
import care.intouch.uikit.ui.textFields.MultilineTextFieldDefaults
import care.intouch.uikit.ui.textFields.MultilineTextFieldDefaults.BLANC_STRING

@Composable
fun TextFieldWithCheckmars(
    modifier: Modifier = Modifier,
    titleText: StringVO = StringVO.Plain(BLANC_STRING),
    subtitleText: StringVO = StringVO.Plain(BLANC_STRING),
    captionText: StringVO = StringVO.Plain(BLANC_STRING),
    checkmarkText: StringVO = StringVO.Plain(BLANC_STRING),
    secondCheckmarkText: StringVO = StringVO.Plain(BLANC_STRING),
    thirdCheckmarkText: StringVO = StringVO.Plain(BLANC_STRING),
    fourthCheckmarkText: StringVO = StringVO.Plain(BLANC_STRING),
    backgroundColor: Color = InTouchTheme.colors.input85,
) {
    Column(
        modifier = modifier.width(MultilineTextFieldDefaults.MinWidth)
    ) {
        val items = listOf(checkmarkText, secondCheckmarkText, thirdCheckmarkText, fourthCheckmarkText)
        val selectedOptions = remember {
            mutableStateOf(setOf<StringVO>())
        }

        if (titleText.value().isNotBlank()
            || subtitleText.value().isNotBlank()
            || captionText.value().isNotBlank()
        ) {
            Column(modifier = Modifier.padding(bottom = 8.dp)) {
                if (titleText.value().isNotBlank()) {
                    Text(
                        text = titleText.value(),
                        style = InTouchTheme.typography.titleSmall,
                        color = InTouchTheme.colors.textBlue,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                if (subtitleText.value().isNotBlank()) {
                    Text(
                        text = subtitleText.value(),
                        modifier = if (titleText.value().isNotBlank()) Modifier
                            .padding(top = 8.dp) else Modifier,
                        style = InTouchTheme.typography.bodySemibold,
                        color = InTouchTheme.colors.textGreen,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                if (captionText.value().isNotBlank()) {
                    Text(
                        text = captionText.value(),
                        modifier = if (subtitleText.value().isNotBlank() || titleText.value()
                                .isNotBlank()
                        )
                            Modifier.padding(top = 2.dp) else Modifier,
                        style = InTouchTheme.typography.caption1Regular,
                        color = InTouchTheme.colors.textGreen,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
                .border(
                    width = 1.dp,
                    color = backgroundColor,
                    shape = RoundedCornerShape(12.dp),
                )
        ) {
            Column (
                modifier = Modifier.fillMaxWidth()
                    .selectableGroup()
            ) {
                items.forEach { item ->
                    Spacer(modifier = Modifier.height(10.dp))
                    CheckmarkWithText(
                        isChecked = selectedOptions.value.contains(item),
                        text = item.value(),
                        modifier = Modifier.padding(start = 24.dp, end = 22.dp),
                        onChangeState = { selected ->
                            val currentSelected = selectedOptions.value.toMutableSet()
                            if (selected) {
                                currentSelected.remove(item)
                                Log.d("Test", "Произошел add в onChangeState")
                            }
                            else {
                                currentSelected.add(item)
                                Log.d("Test", "Произошло удаление в onChangeState")
                            }
                            selectedOptions.value = currentSelected
                        }
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

        }
    }
}

@Preview
@Composable
fun TextFieldWithCheckmarksPreview() {
    InTouchTheme {
        TextFieldWithCheckmars(
            titleText = StringVO.Plain("Title small "),
            subtitleText = StringVO.Plain("Body semi bold "),
            captionText = StringVO.Plain("Caption "),
            checkmarkText = StringVO.Plain("First variant"),
            secondCheckmarkText = StringVO.Plain("Second variant"),
            thirdCheckmarkText = StringVO.Plain("Third variant"),
            fourthCheckmarkText = StringVO.Plain("Fourth variant"),
            modifier = Modifier.padding(45.dp)
        )
    }
}