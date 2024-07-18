package care.intouch.app.feature.home.domain.models

data class DiaryEntry(
    val id: Int = 0,
    val date: String = "",
    val note: String = "",
    val moodList: List<Mood> = listOf(),
    val isSharedWithDoctor: Boolean = false
)
