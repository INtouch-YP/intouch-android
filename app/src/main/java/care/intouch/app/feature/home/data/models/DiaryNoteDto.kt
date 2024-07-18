package care.intouch.app.feature.home.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiaryNoteDto(
    val id: Int,
    val author: Int,
    @SerialName("add_date") val addDate: String,
    val visible: Boolean,
    @SerialName("event_details") val eventDetails: String = "",
    @SerialName("event_details_tags") val eventDetailsTags: String = "",
    @SerialName("thoughts_analysis") val thoughtsAnalysis: String = "",
    @SerialName("thoughts_analysis_tags") val thoughtsAnalysisTags: String = "",
    @SerialName("emotion_type") val emotionType: String = "",
    @SerialName("emotion_type_tags") val emotionTypeTags: String = "",
    @SerialName("physical_sensation") val physicalSensation: String = "",
    @SerialName("physical_sensation_tags") val physicalSensationTags: String = "",
    @SerialName("primary_emotion") val primaryEmotion: String = "",
    @SerialName("clarifying_emotion") val clarifyingEmotion: List<ClarifyingEmotionDto> = listOf()
)
