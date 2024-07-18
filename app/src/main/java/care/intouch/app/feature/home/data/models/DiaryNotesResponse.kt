package care.intouch.app.feature.home.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiaryNotesResponse(@SerialName("results") val diariesNotes: List<DiaryNoteDto>)
