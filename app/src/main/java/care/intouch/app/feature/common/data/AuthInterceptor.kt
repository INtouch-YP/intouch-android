package care.intouch.app.feature.common.data

import care.intouch.app.feature.authorization.data.api.AccountLocalDataSource
import care.intouch.app.feature.authorization.data.models.TokensRequest
import care.intouch.app.feature.authorization.data.models.response.TokensResponse
import care.intouch.app.feature.authorization.domain.dto.AccountModel
import care.intouch.app.feature.common.data.api.TokensApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.HttpURLConnection
import java.util.concurrent.locks.ReentrantLock
import javax.inject.Inject
import kotlin.concurrent.withLock

class AuthInterceptor @Inject constructor(
    private val tokensApiService: TokensApiService,
    private val accountLocalDataSource: AccountLocalDataSource
) : Interceptor {

    private val lock = ReentrantLock()
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = getOrCreateRequestOnChainWithToken(chain, getAccessToken())
        val response = chain.proceed(request)
        if (getAccessToken().isBlank() || getRefreshToken().isBlank()) return response
        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            lock.withLock {
                val oldAccessToken =
                    response.request.header(HEADER_NAME)?.replace(BEARER, EMPTY_STRING)
                val currentAccessToken = getAccessToken()

                if (oldAccessToken != currentAccessToken) {
                    return chain.proceed(
                        createRequestOnResponseWithToken(
                            response,
                            currentAccessToken
                        )
                    )
                } else {
                    try {
                        val newTokens = getNewTokens(getRefreshToken())
                        saveCredentials(userId = getUserId(), newTokens.access, newTokens.refresh)
                        return chain.proceed(
                            createRequestOnResponseWithToken(
                                response,
                                getAccessToken()
                            )
                        )
                    } catch (e: Exception) {
                        clearCredentials()
                    }
                }
            }
        }
        return response
    }

    private fun getOrCreateRequestOnChainWithToken(
        chain: Interceptor.Chain,
        accessToken: String
    ): Request {
        return if (accessToken.isBlank()) {
            chain.request()
        } else chain.request()
            .newBuilder()
            .header(HEADER_NAME, BEARER + accessToken)
            .build()
    }

    private fun createRequestOnResponseWithToken(
        response: Response,
        accessToken: String
    ): Request {
        return response.request.newBuilder()
            .header(HEADER_NAME, "$BEARER${accessToken}")
            .build()
    }


    private fun getNewTokens(refreshTokens: String): TokensResponse =
        runBlocking(Dispatchers.IO) {
            return@runBlocking tokensApiService.getTokensByRefreshToken(
                TokensRequest(refresh = refreshTokens)
            )
        }

    private fun getUserId(): Int =
        runBlocking(Dispatchers.IO) {
            return@runBlocking accountLocalDataSource.getAccountInformation()?.userId ?: 0
        }

    private fun getAccessToken(): String =
        runBlocking(Dispatchers.IO) {
            return@runBlocking accountLocalDataSource.getAccountInformation()?.accessToken
                ?: EMPTY_STRING
        }

    private fun getRefreshToken(): String =
        runBlocking(Dispatchers.IO) {
            return@runBlocking accountLocalDataSource.getAccountInformation()?.refreshToken
                ?: EMPTY_STRING
        }

    private fun saveCredentials(userId: Int, accessToken: String, refreshToken: String) {
        runBlocking(Dispatchers.IO) {
            accountLocalDataSource.saveAccountInformation(
                AccountModel(
                    userId = userId,
                    accessToken = accessToken,
                    refreshToken = refreshToken
                )
            )
        }
    }

    private fun clearCredentials() {
        runBlocking(Dispatchers.IO) {
            accountLocalDataSource.clearAccountInformation()
        }
    }

    companion object {
        const val HEADER_NAME = "Authorization"
        const val BEARER = "Bearer "
        const val EMPTY_STRING = ""
    }
}