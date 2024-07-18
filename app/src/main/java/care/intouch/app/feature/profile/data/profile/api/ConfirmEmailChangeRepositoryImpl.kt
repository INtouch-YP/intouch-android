package care.intouch.app.feature.profile.data.profile.api

import care.intouch.app.feature.authorization.data.models.exception.AuthenticationException
import care.intouch.app.feature.authorization.data.models.mappers.NetworkToUserExceptionMapper.Companion.COULD_NOT_CONVERT_TO_ERROR_RESPONSE
import care.intouch.app.feature.common.data.models.exception.NetworkException
import care.intouch.app.feature.profile.domain.profile.models.EmailChangeResponse
import care.intouch.app.feature.profile.domain.profile.useCase.ConfirmEmailChangeRepository
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ConfirmEmailChangeRepositoryImpl @Inject constructor(
    private val confirmEmailChangeApi: ConfirmEmailChangeApi,
    private val json: Json
) : ConfirmEmailChangeRepository {

    override suspend fun confirmEmailChange(
        id: String,
        token: String
    ): Result<EmailChangeResponse> {
        try {
            val response = confirmEmailChangeApi.confirmEmailChange(id, token)
            return Result.success(response)
        } catch (e: NetworkException) {
            return when (e) {
                is NetworkException.BadRequest -> {
                    val response = handleErrorResponse<EmailChangeResponse>(e.errorBody)
                    Result.failure(
                        NetworkException.BadRequest(
                            response.error ?: "",
                            e.httpStatusCode
                        )
                    )
                }

                else -> {
                    Result.failure(e)
                }
            }
        } catch (e: Exception) {
            return Result.failure(e)
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



