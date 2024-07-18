package care.intouch.app.feature.diary.data.modals

import care.intouch.app.feature.diary.domain.modal.Diary
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiaryDTO(
    @SerialName("add_date")
    val addDate: String,
    @SerialName("author")
    val author: Int,
    @SerialName("author_name")
    val authorName: String,
    @SerialName("clarifying_emotion")
    val clarifyingEmotion: List<String>,
    @SerialName("emotion_type")
    val emotionType: String,
    @SerialName("emotion_type_tags")
    val emotionTypeTags: String,
    @SerialName("event_details")
    val eventDetails: String,
    @SerialName("event_details_tags")
    val eventDetailsTags: String,
    @SerialName("id")
    val id: Int,
    @SerialName("physical_sensations")
    val physicalSensations: String,
    @SerialName("physical_sensations_tags")
    val physicalSensationsTags: String,
    @SerialName("primary_emotion")
    val primaryEmotion: String,
    @SerialName("thoughts_analysis")
    val thoughtsAnalysis: String,
    @SerialName("thoughts_analysis_tags")
    val thoughtsAnalysisTags: String,
    @SerialName("visible")
    val visible: Boolean
)

fun DiaryDTO.toDiary() =
    Diary(id, addDate, clarifyingEmotion, eventDetails, primaryEmotion, visible)