package care.intouch.app.feature.plan.data.network.dto.response

import care.intouch.app.feature.plan.data.network.dto.model.AssignmentDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssignmentsResponse(
    @SerialName("results")
    val assignments: List<AssignmentDto>
) : Response