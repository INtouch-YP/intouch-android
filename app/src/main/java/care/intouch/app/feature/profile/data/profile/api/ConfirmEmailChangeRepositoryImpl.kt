package care.intouch.app.feature.profile.data.profile.api

import android.util.Log
import care.intouch.app.feature.profile.domain.profile.useCase.ConfirmEmailChangeRepository
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ConfirmEmailChangeRepositoryImpl @Inject constructor(
    private val confirmEmailChangeApi: ConfirmEmailChangeApi,
    private val json: Json
): ConfirmEmailChangeRepository {
    override suspend fun confirmEmailChange(id: String, token: String) {
        try {
            confirmEmailChangeApi.confirmEmailChange(id, token)
            Log.d("MY_INTOUCH_TAG","Изменение почты подтверждено")
        } catch (e: Exception) {
            Log.d("MY_INTOUCH_TAG","Мы не подтвердили изменение адреса эл.почты")
        }
    }
}