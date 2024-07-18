package care.intouch.app.feature.home.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssignmentDto(
    val id: Int,
    val title: String,
    val text: String,
    @SerialName("update_date") val updateDate: String,
    @SerialName("add_date") val addDate: String,
    @SerialName("assignment_type") val assignmentType: String,
    val status: String,
    val share: Int,
    val user: Int,
    val visible: Boolean,
    val grade: Int? = 0,
    val review: String? = "",

)