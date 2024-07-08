package care.intouch.app.feature.profile.data.profile.api

import care.intouch.app.feature.authorization.data.models.exception.AuthenticationException
import care.intouch.app.feature.authorization.data.models.mappers.NetworkToUserExceptionMapper.Companion.COULD_NOT_CONVERT_TO_ERROR_RESPONSE
import care.intouch.app.feature.common.data.models.exception.NetworkException
import care.intouch.app.feature.profile.data.profile.models.UpdateUserEmailErrorResponse
import care.intouch.app.feature.profile.data.profile.models.UpdateUserEmailRequest
import care.intouch.app.feature.profile.domain.profile.models.UpdateUserEmailResponse
import care.intouch.app.feature.profile.domain.profile.useCase.UpdateUserEmailRepository
import kotlinx.serialization.json.Json
import javax.inject.Inject

class UpdateUserEmailRepositoryImpl @Inject constructor(
    private val updateUserEmailApi: UpdateUserEmailApi,
    private val json: Json
) : UpdateUserEmailRepository {

    override suspend fun redactUserEmail(newEmail: String): Result<UpdateUserEmailResponse> {
        try {
            val response = updateUserEmailApi.updateUserEmail(UpdateUserEmailRequest(newEmail))
            return Result.success(response)
        } catch (e: NetworkException) {
            return when (e) {
                is NetworkException.BadRequest -> {
                    val response = handleErrorResponse<UpdateUserEmailErrorResponse>(e.errorBody)
                    Result.failure(
                        NetworkException.BadRequest(
                            response.detail?.get(0) ?: "",
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