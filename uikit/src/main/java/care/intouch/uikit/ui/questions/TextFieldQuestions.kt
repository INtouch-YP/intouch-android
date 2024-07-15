package care.intouch.uikit.ui.questions

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.textFields.MultilineTextFieldDefaults
import care.intouch.uikit.ui.textFields.MultilineTextFieldDefaults.BLANC_STRING
import care.intouch.uikit.ui.textFields.MultilineTextFieldDefaults.TextPadding

@Composable
fun TextFieldQuestion(
    modifier: Modifier = Modifier,
    topTitleText: StringVO = StringVO.Plain(BLANC_STRING),
    bottomTitleText: StringVO = StringVO.Plain(BLANC_STRING),
    topSubtitleText: StringVO = StringVO.Plain(BLANC_STRING),
    bottomSubtitleText: StringVO = StringVO.Plain(BLANC_STRING),
    topCaptionText: StringVO = StringVO.Plain(BLANC_STRING),
    bottomCaptionText: StringVO = StringVO.Plain(BLANC_STRING),
    textPadding: Dp = TextPadding,
    isError: Boolean,
    enabled: Boolean,
    backgroundColor: Color = InTouchTheme.colors.input85,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Column(
        modifier = modifier.width(MultilineTextFieldDefaults.MinWidth)
    ) {
        Box(
            modifier = Modifier
                .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
                .border(
                    width = 1.dp,
                    color = when {
                        isError && enabled -> InTouchTheme.colors.errorRed
                        isFocused && enabled -> InTouchTheme.colors.accentGreen
                        else -> backgroundColor
                    },
                    shape = RoundedCornerShape(12.dp),
                ),
        ) {
            Column (
                modifier = Modifier.padding(vertical = 14.dp)
            ) {
                if (topTitleText.value().isNotBlank()
                    || topSubtitleText.value().isNotBlank()
                    || topCaptionText.value().isNotBlank()
                ) {
                    Column(modifier = Modifier
                        .padding(bottom = textPadding)
                        .padding(horizontal = 16.dp)) {
                        if (topTitleText.value().isNotBlank()) {
                            Text(
                                text = topTitleText.value(),
                                style = InTouchTheme.typography.bodyBold,
                                color = if (enabled) {
                                    InTouchTheme.colors.textBlue
                                } else {
                                    InTouchTheme.colors.textBlue50
                                },
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        if (topSubtitleText.value().isNotBlank()) {
                            Text(
                                text = topSubtitleText.value(),
                                modifier = if (topTitleText.value().isNotBlank()) Modifier
                                    .padding(top = 8.dp) else Modifier,
                                style = InTouchTheme.typography.bodySemibold,
                                color = if (enabled) {
                                    InTouchTheme.colors.textGreen
                                } else {
                                    InTouchTheme.colors.textGreen40
                                },
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        if (topCaptionText.value().isNotBlank()) {
                            Text(
                                text = topCaptionText.value(),
                                modifier = if (topSubtitleText.value().isNotBlank() || topTitleText.value()
                                        .isNotBlank()
                                )
                                    Modifier.padding(top = 2.dp) else Modifier,
                                style = InTouchTheme.typography.caption1Regular,
                                color = if (enabled) {
                                    InTouchTheme.colors.textGreen
                                } else {
                                    InTouchTheme.colors.textGreen40
                                },
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                if (bottomTitleText.value().isNotBlank()
                    || bottomSubtitleText.value().isNotBlank()
                    || bottomCaptionText.value().isNotBlank()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(bottom = textPadding)
                            .padding(horizontal = 16.dp)
                    ) {
                        if (bottomTitleText.value().isNotBlank()) {
                            Text(
                                text = bottomTitleText.value(),
                                style = InTouchTheme.typography.titleSmall,
                                color = if (enabled) {
                                    InTouchTheme.colors.textBlue
                                } else {
                                    InTouchTheme.colors.textBlue50
                                },
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        if (bottomSubtitleText.value().isNotBlank()) {
                            Text(
                                text = bottomSubtitleText.value(),
                                modifier = if (bottomTitleText.value().isNotBlank()) Modifier
                                    .padding(top = 8.dp) else Modifier,
                                style = InTouchTheme.typography.bodySemibold,
                                color = if (enabled) {
                                    InTouchTheme.colors.textGreen
                                } else {
                                    InTouchTheme.colors.textGreen40
                                },
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        if (bottomCaptionText.value().isNotBlank()) {
                            Text(
                                text = bottomCaptionText.value(),
                                modifier = if (bottomSubtitleText.value()
                                        .isNotBlank() || bottomTitleText.value()
                                        .isNotBlank()
                                )
                                    Modifier.padding(top = 2.dp) else Modifier,
                                style = InTouchTheme.typography.caption1Regular,
                                color = if (enabled) {
                                    InTouchTheme.colors.textGreen
                                } else {
                                    InTouchTheme.colors.textGreen40
                                },
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TextFieldQuestionPreview() {
    InTouchTheme {
        TextFieldQuestion(
            topTitleText = StringVO.Plain("Top Title small "),
            bottomTitleText = StringVO.Plain("Bottom Title small"),
            topSubtitleText = StringVO.Plain("Top Body semi bold "),
            bottomSubtitleText = StringVO.Plain("Bottom Body semi bold "),
            topCaptionText = StringVO.Plain("Top Caption "),
            bottomCaptionText = StringVO.Plain("Bottom Caption"),
            isError = false,
            enabled = true,
            modifier = Modifier.padding(45.dp)
        )
    }
}