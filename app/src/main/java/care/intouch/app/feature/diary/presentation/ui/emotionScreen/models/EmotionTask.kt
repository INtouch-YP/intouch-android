package care.intouch.app.feature.diary.presentation.ui.emotionScreen.models

import care.intouch.uikit.common.ImageVO

data class EmotionTask(
    val imageVO: ImageVO,
    val bigImageVO: ImageVO,
    val emotionDesc: EmotionDesc,
)