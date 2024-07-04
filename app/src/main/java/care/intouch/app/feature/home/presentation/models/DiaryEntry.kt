package care.intouch.app.feature.home.presentation.models

data class DiaryEntry(
    val id: Int,
    val data: String,
    val note: String,
    val moodList: List<Mood>,
    val isSharedWithDoctor: Boolean
)
