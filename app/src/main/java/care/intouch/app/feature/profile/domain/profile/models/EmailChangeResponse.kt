package care.intouch.app.feature.profile.domain.profile.models

import kotlinx.serialization.Serializable

@Serializable
data class EmailChangeResponse(
    val error: String? = null,
    val message: String? = null,
)
