package care.intouch.app.feature.profile.data.profile.api

import care.intouch.app.feature.authorization.data.models.exception.AuthenticationException
import care.intouch.app.feature.authorization.data.models.mappers.NetworkToUserExceptionMapper.Companion.COULD_NOT_CONVERT_TO_ERROR_RESPONSE
import care.intouch.app.feature.common.data.models.exception.NetworkException
import care.intouch.app.feature.profile.data.profile.models.UpdateUserEmailErrorResponse
import care.intouch.app.feature.profile.data.profile.models.UpdateUserEmailRequest
import care.intouch.app.feature.profile.domain.profile.models.RedactUserEmailResponse
import care.intouch.app.feature.profile.domain.profile.useCase.RedactUserEmailRepository
import kotlinx.serialization.json.Json
import javax.inject.Inject

class RedactUserEmailRepositoryImpl @Inject constructor(
    private val redactUserEmailApi: RedactUserEmailApi,
    private val json: Json
) : RedactUserEmailRepository {

    override suspend fun redactUserEmail(newEmail: String): Result<RedactUserEmailResponse> {
        return
            try {
                val response = redactUserEmailApi.updateUserEmail(UpdateUserEmailRequest(newEmail))
                Result.success(RedactUserEmailResponse.RedactUserEmailSuccess())
            } catch (e: NetworkException) {
                when(e){
                    is NetworkException.BadRequest -> {
                        val response = handleErrorResponse<UpdateUserEmailErrorResponse>(e.errorBody)
                        Result.failure(Exception(response.detail?.get(0)?: ""))
                    }
                    else -> {
                        val response = handleErrorResponse<UpdateUserEmailErrorResponse>(e.errorBody)
                    }
                }


            }
        }


    private inline fun <reified T> handleErrorResponse(errorMessage: String): T {
        try {
            return json.decodeFromString<T>(errorMessage)

        } catch (e: Exception) {
            throw AuthenticationException.Undefined(COULD_NOT_CONVERT_TO_ERROR_RESPONSE)
        }
}

    }