package care.intouch.uikit.ui.questions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.IntouchButton
import care.intouch.uikit.ui.buttons.SecondaryButtonDark
import care.intouch.uikit.ui.textFields.MultilineTextFieldDefaults.BLANC_STRING

@Composable
fun PopupQuestions(
    inTouchButtonClick: () -> Unit,
    secondaryButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    firstLineText: StringVO = StringVO.Plain(BLANC_STRING),
    secondLineText: StringVO = StringVO.Plain(BLANC_STRING),
    intouchButtonText: StringVO = StringVO.Plain(BLANC_STRING),
    secondaryButtonText: StringVO = StringVO.Plain(BLANC_STRING),
    backgroundColor: Color = InTouchTheme.colors.input85,
) {
            Column (
                modifier = modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(backgroundColor),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier) {
                    Column {
                        Text(
                            text = firstLineText.value(),
                            style = InTouchTheme.typography.filtersSemibold,
                            color = InTouchTheme.colors.textBlue,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 48.dp)
                                .padding(horizontal = 28.dp),
                            textAlign = TextAlign.Center
                        )
                        if (secondLineText.value().isNotBlank()) {
                            Text(
                                text = secondLineText.value(),
                                style = InTouchTheme.typography.filtersSemibold,
                                color = InTouchTheme.colors.textBlue,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(horizontal = 28.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                }
                Spacer(modifier = Modifier.height(48.dp))
                IntouchButton(
                    onClick = {
                        inTouchButtonClick.invoke()
                    },
                    text = intouchButtonText,
                    contentPadding = PaddingValues(vertical = 13.dp),
                    modifier = Modifier
                        .padding(horizontal = 28.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                SecondaryButtonDark(
                    onClick = {
                        secondaryButtonClick.invoke()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 48.dp)
                        .padding(horizontal = 28.dp),
                    text = secondaryButtonText,
                    textStyle = InTouchTheme.typography.titleSmall
                )
            }
        }

@Preview(showBackground = true)
@Composable
fun PopupQuestionsPreview() {
    InTouchTheme {
        PopupQuestions(
            firstLineText = StringVO.Plain("Title small on first line"),
            secondLineText = StringVO.Plain("Title small"),
            intouchButtonText = StringVO.Plain("Back"),
            secondaryButtonText = StringVO.Plain("Complete as is"),
            inTouchButtonClick = {

            },
            secondaryButtonClick = {

            }
        )
    }
}