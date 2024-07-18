package care.intouch.app.feature.diary.domain.modal


data class Diary(
    val id: Int = 0,
    val addDate: String = "",
    val clarifyingEmotion: List<String>,
    val eventDetails: String,
    val primaryEmotion: String,
    val visible: Boolean,
    val thoughtsAnalysis: String = "",
    val physicalSensations: String = "",
    val emotionType: String = "",
)
