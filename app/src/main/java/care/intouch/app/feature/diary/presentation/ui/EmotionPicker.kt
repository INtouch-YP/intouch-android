package care.intouch.app.feature.diary.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.feature.diary.presentation.ui.models.newnote.Emotion
import care.intouch.app.feature.diary.presentation.ui.models.newnote.EmotionResources
import care.intouch.uikit.R
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun EmotionPicker(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit,
    onEditClick: () -> Unit,
    onTrashClick: () -> Unit,
    buttonText: StringVO,
    onValueChange: (Emotion) -> Unit,
    selectedEmotion: Emotion,
    backgroundColor: Color = InTouchTheme.colors.input85,
) {
    var text by remember {
        mutableStateOf("")
    }

    Column {
        TextField(
            modifier = modifier
                .wrapContentSize()
                .fillMaxWidth(),
            value = text.toString(),
            enabled = false,
            onValueChange = { newEmotion ->
                text = newEmotion
                val emotion = mapTextToEmotion(newEmotion)
                if (emotion != null) {
                    onValueChange(emotion)
                }

            },
            colors = TextFieldDefaults.colors(
                cursorColor = InTouchTheme.colors.transparent,
                disabledIndicatorColor = InTouchTheme.colors.transparent,
                focusedIndicatorColor = InTouchTheme.colors.transparent,
                unfocusedIndicatorColor = InTouchTheme.colors.transparent,
                focusedContainerColor = backgroundColor,
                unfocusedContainerColor = backgroundColor,
                disabledContainerColor = backgroundColor,
                errorContainerColor = backgroundColor,
                errorTrailingIconColor = InTouchTheme.colors.mainGreen40,
                focusedTrailingIconColor = InTouchTheme.colors.mainGreen40,
                disabledTrailingIconColor = InTouchTheme.colors.mainGreen40,
                unfocusedTrailingIconColor = InTouchTheme.colors.mainGreen40,
            ),
            shape = RoundedCornerShape(12.dp),
            trailingIcon = {
                if (!selectedEmotion.equals(Emotion.BLANC)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(onClick = { onEditClick.invoke() }) {
                            Icon(
                                ImageVO.Resource(R.drawable.icon_edit).painter(),
                                contentDescription = "edit icon button"
                            )
                        }
                        IconButton(onClick = { onTrashClick.invoke() })
                        {
                            Icon(
                                ImageVO.Resource(R.drawable.icon_trash).painter(),
                                contentDescription = "trash icon button"
                            )
                        }
                    }
                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            painter = ImageVO.Resource(care.intouch.uikit.R.drawable.icon_terrible_no_text)
                                .painter(), contentDescription = "filler icon"
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            painter = ImageVO.Resource(care.intouch.uikit.R.drawable.icon_bad_no_text)
                                .painter(), contentDescription = "filler icon"
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            painter = ImageVO.Resource(care.intouch.uikit.R.drawable.icon_okay_no_text)
                                .painter(), contentDescription = "filler icon"
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                }
            },
            placeholder = {
                when (selectedEmotion) {
                    Emotion.TERRIBLE -> {
                        Icon(
                            painter = painterResource(id = EmotionResources.emotionImages.terrible),
                            contentDescription = null,
                            tint = InTouchTheme.colors.mainGreen
                        )
                    }

                    Emotion.GOOD -> {
                        Icon(
                            painter = painterResource(id = EmotionResources.emotionImages.good),
                            contentDescription = null,
                            tint = InTouchTheme.colors.mainGreen
                        )
                    }

                    Emotion.OKAY -> {
                        Icon(
                            painter = painterResource(id = EmotionResources.emotionImages.okay),
                            contentDescription = null,
                            tint = InTouchTheme.colors.mainGreen
                        )
                    }

                    Emotion.BAD -> {
                        Icon(
                            painter = painterResource(id = EmotionResources.emotionImages.bad),
                            contentDescription = null,
                            tint = InTouchTheme.colors.mainGreen
                        )
                    }

                    Emotion.GREAT -> {
                        Icon(
                            painter = painterResource(id = EmotionResources.emotionImages.great),
                            contentDescription = null,
                            tint = InTouchTheme.colors.mainGreen
                        )
                    }

                    Emotion.BLANC -> {
                        IntouchIconTextButton(
                            onClick = { onAddClick.invoke() },
                            modifier = Modifier.height(38.dp),
                            text = buttonText
                        )
                    }
                }
            },
            readOnly = true,
        )
    }
}

private fun mapTextToEmotion(text: String): Emotion? {
    return when (text.lowercase()) {
        "blanc" -> Emotion.BLANC
        "terrible" -> Emotion.TERRIBLE
        "good" -> Emotion.GOOD
        "okay" -> Emotion.OKAY
        "bad" -> Emotion.BAD
        "great" -> Emotion.GREAT
        else -> null
    }
}

@Composable
@Preview(showBackground = false)
fun EmotionPickerPreview() {
    InTouchTheme {
        val buttonText = StringVO.Resource(care.intouch.app.R.string.add_emotions_button)
        EmotionPicker(
            selectedEmotion = Emotion.BLANC,
            modifier = Modifier.padding(45.dp),
            buttonText = buttonText,
            onAddClick = {},
            onEditClick = {},
            onValueChange = {},
            onTrashClick = {}
        )
    }
}

@Composable
@Preview(showBackground = false)
fun EmotionPickerEmotionPreview() {
    InTouchTheme {
        val selectEmotion = Emotion.GOOD
        val buttonText = StringVO.Resource(care.intouch.app.R.string.add_emotions_button)
        EmotionPicker(
            selectedEmotion = selectEmotion,
            modifier = Modifier,
            onAddClick = {},
            onEditClick = {},
            onValueChange = {},
            onTrashClick = {},
            buttonText = buttonText
        )
    }
}