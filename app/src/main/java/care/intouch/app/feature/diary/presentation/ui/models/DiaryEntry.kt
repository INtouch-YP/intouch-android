package care.intouch.app.feature.diary.presentation.ui.models

data class DiaryEntry(
    val id: Int,
    val data: String,
    val note: String,
    val moodList: List<Mood>,
    var sharedWithDoc: Boolean
)