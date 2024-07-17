package care.intouch.app.feature.profile.domain.profile.useCase


interface ConfirmEmailChangeRepository {
    suspend fun confirmEmailChange(id: String, token: String)
}