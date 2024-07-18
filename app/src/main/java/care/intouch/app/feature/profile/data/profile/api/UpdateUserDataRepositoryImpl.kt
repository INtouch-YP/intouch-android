package care.intouch.app.feature.profile.data.profile.api

import care.intouch.app.feature.authorization.data.models.exception.AuthenticationException
import care.intouch.app.feature.authorization.data.models.mappers.NetworkToUserExceptionMapper.Companion.COULD_NOT_CONVERT_TO_ERROR_RESPONSE
import care.intouch.app.feature.common.data.models.exception.NetworkException
import care.intouch.app.feature.profile.data.profile.models.UpdateUserDataRequest
import care.intouch.app.feature.profile.domain.profile.models.ProfileData
import care.intouch.app.feature.profile.domain.profile.models.UpdateUserDataResponse
import care.intouch.app.feature.profile.domain.profile.useCase.UpdateUserDataRepository
import kotlinx.serialization.json.Json
import javax.inject.Inject

class UpdateUserDataRepositoryImpl @Inject constructor(
    private val updateUserDataApi: UpdateUserDataApi,
    private val json: Json
) : UpdateUserDataRepository {
    override suspend fun redactUserData(
        userData: ProfileData,
        id: Int
    ): Result<UpdateUserDataResponse> {
        try {
            val response = updateUserDataApi.updateUserData(
                id,
                UpdateUserDataRequest(firstName = userData.firstName, lastName = userData.lastName)
            )
            return Result.success(
                UpdateUserDataResponse.UpdateUserDataSuccess(
                    ProfileData(
                        firstName = response.firstName,
                        lastName = response.lastName
                    )
                )
            )
        } catch (e: NetworkException) {
            return when (e) {
                is NetworkException.BadRequest -> {
                    val response = handleErrorResponse<UpdateUserDataResponse.UpdateUserDataError>(e.errorBody)
                    Result.failure(
                        NetworkException.BadRequest(
                            response.details,
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