package care.intouch.app.feature.profile.data.profile.api

import care.intouch.app.feature.profile.domain.profile.useCase.ConfirmEmailChangeRepository
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ConfirmEmailChangeRepositoryImpl @Inject constructor(
    private val confirmEmailChangeApi: ConfirmEmailChangeApi,
    private val json: Json
): ConfirmEmailChangeRepository {
    override suspend fun confirmEmailChange(id: String, token: String) {

    }
}