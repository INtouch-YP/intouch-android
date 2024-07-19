package care.intouch.app.feature.home.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssignmentsResponse(@SerialName("results") val assignments: List<AssignmentDto>)