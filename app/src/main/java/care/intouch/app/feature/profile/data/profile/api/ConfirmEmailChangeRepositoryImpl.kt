package care.intouch.app.feature.profile.data.profile.api

import care.intouch.app.feature.profile.domain.profile.useCase.ConfirmEmailChangeRepository
import kotlinx.serialization.json.Json

class ConfirmEmailChangeRepositoryImpl(
    private val confirmEmailChangeApi: ConfirmEmailChangeApi,
    private val json: Json
): ConfirmEmailChangeRepository {
    override suspend fun confirmEmailChange(id: String, token: String) {
        TODO("Not yet implemented")
    }
}