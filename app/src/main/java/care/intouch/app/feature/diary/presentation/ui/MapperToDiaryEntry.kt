package care.intouch.app.feature.diary.presentation.ui

import care.intouch.app.feature.diary.domain.modal.Diary
import care.intouch.app.feature.diary.presentation.ui.models.DiaryEntry
import care.intouch.app.feature.diary.presentation.ui.models.Mood
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun mapperToDiaryEntry(diary: Diary): DiaryEntry {
    val inputDateFormat =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.getDefault())
    val date = inputDateFormat.parse(diary.addDate)
    val calendar = Calendar.getInstance().apply { time = date }
    val monthFormat = SimpleDateFormat("MMM", Locale.getDefault())
    val month = monthFormat.format(date).lowercase(Locale.ROOT)
    val day = String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH))

    return DiaryEntry(
        id = diary.id,
        data = "$month $day",
        note = diary.eventDetails,
        moodList = diary.clarifyingEmotion.map {
            Mood(it)
        },
        sharedWithDoc = diary.visible
    )
}