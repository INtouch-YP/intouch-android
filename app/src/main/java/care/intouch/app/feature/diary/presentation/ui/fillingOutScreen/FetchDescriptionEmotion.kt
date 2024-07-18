package care.intouch.app.feature.diary.presentation.ui.fillingOutScreen

import care.intouch.app.R
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.models.EmotionDescriptionEnum
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.models.EmotionDescriptionTask
import care.intouch.uikit.common.StringVO

internal fun getEmotionDesc(list: List<EmotionDescriptionEnum>): MutableList<EmotionDescriptionTask> {
    val result: MutableList<EmotionDescriptionTask> = mutableListOf()
    list.forEach {
        when (it) {
            EmotionDescriptionEnum.Loss -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(
                        R.string.loss_clarifying_emotional
                    ), EmotionDescriptionEnum.Loss
                )
            )

            EmotionDescriptionEnum.Humility -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.humility_clarifying_emotional),
                    EmotionDescriptionEnum.Humility
                )
            )

            EmotionDescriptionEnum.Anxiety -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(
                        R.string.anxiety_clarifying_emotional
                    ), EmotionDescriptionEnum.Anxiety
                )
            )

            EmotionDescriptionEnum.Embarrassment -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.embarrassment_clarifying_emotional),
                    EmotionDescriptionEnum.Embarrassment
                )
            )

            EmotionDescriptionEnum.Depression -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.depression_clarifying_emotional),
                    EmotionDescriptionEnum.Depression
                )
            )

            EmotionDescriptionEnum.Fear -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(
                        R.string.fear_clarifying_emotional
                    ), EmotionDescriptionEnum.Fear
                )
            )

            EmotionDescriptionEnum.Anger -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.anger_clarifying_emotional),
                    EmotionDescriptionEnum.Anger
                )
            )

            EmotionDescriptionEnum.Excitement -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(
                        R.string.excitement_clarifying_emotional
                    ), EmotionDescriptionEnum.Excitement
                )
            )

            EmotionDescriptionEnum.Shame -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.shame_clarifying_emotional),
                    EmotionDescriptionEnum.Shame
                )
            )

            EmotionDescriptionEnum.Disgust -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.disgust_clarifying_emotional),
                    EmotionDescriptionEnum.Disgust
                )
            )

            EmotionDescriptionEnum.Guilt -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.guilt_clarifying_emotional),
                    EmotionDescriptionEnum.Guilt
                )
            )

            EmotionDescriptionEnum.Nervousness -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.nervousness_clarifying_emotional),
                    EmotionDescriptionEnum.Nervousness
                )
            )

            EmotionDescriptionEnum.Sadness -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.sadness_clarifying_emotional),
                    EmotionDescriptionEnum.Sadness
                )
            )

            EmotionDescriptionEnum.Exhaustion -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.exhaustion_clarifying_emotional),
                    EmotionDescriptionEnum.Exhaustion
                )
            )

            EmotionDescriptionEnum.Impatience -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.impatience_clarifying_emotional),
                    EmotionDescriptionEnum.Impatience
                )
            )

            EmotionDescriptionEnum.Dissapointment -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.disappointment_clarifying_emotional),
                    EmotionDescriptionEnum.Dissapointment
                )
            )

            EmotionDescriptionEnum.Confusion -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.confusion_clarifying_emotional),
                    EmotionDescriptionEnum.Confusion
                )
            )

            EmotionDescriptionEnum.Loneliness -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.loneliness_clarifying_emotional),
                    EmotionDescriptionEnum.Loneliness
                )
            )

            EmotionDescriptionEnum.Acceptance -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.acceptance_clarifying_emotional),
                    EmotionDescriptionEnum.Acceptance
                )
            )

            EmotionDescriptionEnum.Frustration -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.frustration_clarifying_emotional),
                    EmotionDescriptionEnum.Frustration
                )
            )

            EmotionDescriptionEnum.Laziness -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.laziness_clarifying_emotional),
                    EmotionDescriptionEnum.Laziness
                )
            )

            EmotionDescriptionEnum.Pride -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.pride_clarifying_emotional),
                    EmotionDescriptionEnum.Pride
                )
            )

            EmotionDescriptionEnum.Hope -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.hope_clarifying_emotional),
                    EmotionDescriptionEnum.Hope
                )
            )

            EmotionDescriptionEnum.Calmness -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.calmness_clarifying_emotional),
                    EmotionDescriptionEnum.Calmness
                )
            )

            EmotionDescriptionEnum.Gratitude -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.gratitude_clarifying_emotional),
                    EmotionDescriptionEnum.Gratitude
                )
            )

            EmotionDescriptionEnum.Interest -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.interest_clarifying_emotional),
                    EmotionDescriptionEnum.Interest
                )
            )

            EmotionDescriptionEnum.Respect -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.respect_clarifying_emotional),
                    EmotionDescriptionEnum.Respect
                )
            )

            EmotionDescriptionEnum.Enthusiasm -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.enthusiasm_clarifying_emotional),
                    EmotionDescriptionEnum.Enthusiasm
                )
            )

            EmotionDescriptionEnum.Happiness -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.happiness_clarifying_emotional),
                    EmotionDescriptionEnum.Happiness
                )
            )

            EmotionDescriptionEnum.Inspiration -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.inspiration_clarifying_emotional),
                    EmotionDescriptionEnum.Inspiration
                )
            )

            EmotionDescriptionEnum.Joy -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(
                        R.string.joy_clarifying_emotional
                    ), EmotionDescriptionEnum.Joy
                )
            )

            EmotionDescriptionEnum.Love -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.love_clarifying_emotional),
                    EmotionDescriptionEnum.Love
                )
            )

            EmotionDescriptionEnum.Amazement -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.amazement_clarifying_emotional),
                    EmotionDescriptionEnum.Amazement
                )
            )

            EmotionDescriptionEnum.Satisfaction -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.satisfaction_clarifying_emotional),
                    EmotionDescriptionEnum.Satisfaction
                )
            )

            EmotionDescriptionEnum.SelfLove -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.self_love_clarifying_emotional),
                    EmotionDescriptionEnum.SelfLove
                )
            )

            EmotionDescriptionEnum.Euphoria -> result.add(
                EmotionDescriptionTask(
                    StringVO.Resource(R.string.euphoria_clarifying_emotional),
                    EmotionDescriptionEnum.Euphoria
                )
            )
        }
    }
    return result
}

internal fun fetchDescription(): List<EmotionDescriptionTask> {
    return listOf(
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
            StringVO.Resource(R.string.anger_clarifying_emotional),
            EmotionDescriptionEnum.Anger
        ),
        EmotionDescriptionTask(
            StringVO.Resource(R.string.excitement_clarifying_emotional),
            EmotionDescriptionEnum.Excitement
        ),
        EmotionDescriptionTask(
            StringVO.Resource(R.string.shame_clarifying_emotional),
            EmotionDescriptionEnum.Shame
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
            StringVO.Resource(R.string.nervousness_clarifying_emotional),
            EmotionDescriptionEnum.Nervousness
        ),
        EmotionDescriptionTask(
            StringVO.Resource(R.string.sadness_clarifying_emotional),
            EmotionDescriptionEnum.Sadness
        ),
        EmotionDescriptionTask(
            StringVO.Resource(R.string.exhaustion_clarifying_emotional),
            EmotionDescriptionEnum.Exhaustion
        ),
        EmotionDescriptionTask(
            StringVO.Resource(R.string.impatience_clarifying_emotional),
            EmotionDescriptionEnum.Impatience
        ),
        EmotionDescriptionTask(
            StringVO.Resource(R.string.disappointment_clarifying_emotional),
            EmotionDescriptionEnum.Dissapointment
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
            StringVO.Resource(R.string.frustration_clarifying_emotional),
            EmotionDescriptionEnum.Frustration
        ),
        EmotionDescriptionTask(
            StringVO.Resource(R.string.laziness_clarifying_emotional),
            EmotionDescriptionEnum.Laziness
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
            StringVO.Resource(R.string.interest_clarifying_emotional),
            EmotionDescriptionEnum.Interest
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
            StringVO.Resource(R.string.happiness_clarifying_emotional),
            EmotionDescriptionEnum.Happiness
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
            StringVO.Resource(R.string.joy_clarifying_emotional),
            EmotionDescriptionEnum.Joy
        ),
        EmotionDescriptionTask(
            StringVO.Resource(R.string.love_clarifying_emotional),
            EmotionDescriptionEnum.Love
        )
    )
}