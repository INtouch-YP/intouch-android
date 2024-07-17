package care.intouch.app.feature.diary.presentation.ui.models.newnote

import care.intouch.uikit.R

enum class Emotion {
    BLANC,
    TERRIBLE,
    GOOD,
    OKAY,
    BAD,
    GREAT
}

data class EmotionImage(
    val terrible: Int,
    val good: Int,
    val okay: Int,
    val bad: Int,
    val great: Int
)

data object EmotionResources {
    val emotionImages = EmotionImage(
        terrible = R.drawable.icon_terrible_no_text,
        good = R.drawable.icon_good_no_text,
        okay = R.drawable.icon_okay_no_text,
        bad = R.drawable.icon_bad_no_text,
        great = R.drawable.icon_great_no_text
    )
}