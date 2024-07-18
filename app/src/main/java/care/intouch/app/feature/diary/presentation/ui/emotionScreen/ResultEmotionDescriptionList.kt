package care.intouch.app.feature.diary.presentation.ui.emotionScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.R
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.models.EmotionDescriptionEnum
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.models.EmotionDescriptionTask
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.cards.EmotionDescriptionCard

@Composable
fun ResultEmotionDescriptionList(items: List<EmotionDescriptionTask>) {
    val columns = 3
    Column(modifier = Modifier.fillMaxSize()) {
        for (rowIndex in items.indices step columns) {
            Row(modifier = Modifier.fillMaxWidth()) {
                for (columnIndex in 0 until columns) {
                    val itemIndex = rowIndex + columnIndex
                    if (itemIndex < items.size) {
                        Box(
                            modifier = Modifier
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            EmotionDescriptionCard(
                                modifier = Modifier.padding(bottom = 6.dp),
                                text = items[itemIndex].text,
                                onClick = {},
                                selected = false
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ResultEmotionDescriptionList() {
    InTouchTheme {
        ResultEmotionDescriptionList(
            items = listOf(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.loss_clarifying_emotional),
                    EmotionDescriptionEnum.Loss
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.humility_clarifying_emotional),
                    EmotionDescriptionEnum.Humility
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.anxiety_clarifying_emotional),
                    EmotionDescriptionEnum.Anxiety
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.embarrassment_clarifying_emotional),
                    EmotionDescriptionEnum.Embarrassment
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.depression_clarifying_emotional),
                    EmotionDescriptionEnum.Depression
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.fear_clarifying_emotional),
                    EmotionDescriptionEnum.Fear
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.excitement_clarifying_emotional),
                    EmotionDescriptionEnum.Excitement
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.anger_clarifying_emotional),
                    EmotionDescriptionEnum.Anger
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.disgust_clarifying_emotional),
                    EmotionDescriptionEnum.Disgust
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.guilt_clarifying_emotional),
                    EmotionDescriptionEnum.Guilt
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.shame_clarifying_emotional),
                    EmotionDescriptionEnum.Shame
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.nervousness_clarifying_emotional),
                    EmotionDescriptionEnum.Nervousness
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.disappointment_clarifying_emotional),
                    EmotionDescriptionEnum.Dissapointment
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.sadness_clarifying_emotional),
                    EmotionDescriptionEnum.Sadness
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.impatience_clarifying_emotional),
                    EmotionDescriptionEnum.Impatience
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.exhaustion_clarifying_emotional),
                    EmotionDescriptionEnum.Exhaustion
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.frustration_clarifying_emotional),
                    EmotionDescriptionEnum.Frustration
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.confusion_clarifying_emotional),
                    EmotionDescriptionEnum.Confusion
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.loneliness_clarifying_emotional),
                    EmotionDescriptionEnum.Loneliness
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.acceptance_clarifying_emotional),
                    EmotionDescriptionEnum.Acceptance
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.laziness_clarifying_emotional),
                    EmotionDescriptionEnum.Laziness
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.interest_clarifying_emotional),
                    EmotionDescriptionEnum.Interest
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.pride_clarifying_emotional),
                    EmotionDescriptionEnum.Pride
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.hope_clarifying_emotional),
                    EmotionDescriptionEnum.Hope
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.calmness_clarifying_emotional),
                    EmotionDescriptionEnum.Calmness
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.gratitude_clarifying_emotional),
                    EmotionDescriptionEnum.Gratitude
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.happiness_clarifying_emotional),
                    EmotionDescriptionEnum.Happiness
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.respect_clarifying_emotional),
                    EmotionDescriptionEnum.Respect
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.enthusiasm_clarifying_emotional),
                    EmotionDescriptionEnum.Enthusiasm
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.satisfaction_clarifying_emotional),
                    EmotionDescriptionEnum.Satisfaction
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.self_love_clarifying_emotional),
                    EmotionDescriptionEnum.SelfLove
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.joy_clarifying_emotional),
                    EmotionDescriptionEnum.Joy
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.inspiration_clarifying_emotional),
                    EmotionDescriptionEnum.Inspiration
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.amazement_clarifying_emotional),
                    EmotionDescriptionEnum.Amazement
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.euphoria_clarifying_emotional),
                    EmotionDescriptionEnum.Euphoria
                ),
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.love_clarifying_emotional),
                    EmotionDescriptionEnum.Love
                )
            )
        )
    }
}