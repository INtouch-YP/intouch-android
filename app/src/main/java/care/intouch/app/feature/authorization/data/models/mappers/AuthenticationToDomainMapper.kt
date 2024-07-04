package care.intouch.app.feature.authorization.data.models.mappers

import care.intouch.app.feature.authorization.data.models.exception.AuthenticationException
import care.intouch.app.feature.authorization.data.models.response.ConfirmEmailResponse
import care.intouch.app.feature.authorization.data.models.response.TokensResponse
import care.intouch.app.feature.authorization.domain.models.Authentication
import care.intouch.app.feature.authorization.domain.models.AuthenticationToken
import javax.inject.Inject

class AuthenticationToDomainMapper @Inject constructor() {
    fun toAuthenticationOutputData(response: ConfirmEmailResponse): Authentication {
        if (response.accessToken.isNullOrBlank() || response.refreshToken.isNullOrBlank()) {
            when {
                response.message != null -> {
                    throw AuthenticationException.Authentication.AlreadyActivated(
                        message = response.message
                    )
                }

                response.error != null -> {
                    throw AuthenticationException.Authentication.InvalidToken(
                        message = response.error
                    )
                }

                else -> {
                    throw AuthenticationException.Undefined(
                        message = SOMETHING_WENT_WRONG
                    )
                }
            }
        } else {
            return Authentication(
                accessToken = response.accessToken,
                refreshToken = response.refreshToken,
                message = response.message ?: BLANC_STRING
            )
        }
    }

    fun toAuthenticationTokenOutputData(response: TokensResponse): AuthenticationToken {
        return AuthenticationToken(
            accessToken = response.access,
            refreshToken = response.refresh,
        )
    }

    companion object {
        const val BLANC_STRING = ""
        const val SOMETHING_WENT_WRONG = "Something went wrong"
    }
}