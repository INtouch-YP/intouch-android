package care.intouch.app.feature.profile.domain.profile.models

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserEmailResponse(
    val message: String
)
