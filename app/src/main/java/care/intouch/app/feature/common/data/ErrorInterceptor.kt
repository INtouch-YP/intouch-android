package care.intouch.app.feature.common.data

import care.intouch.app.feature.common.data.api.NetworkConnectionProvider
import care.intouch.app.feature.common.data.models.exception.NetworkException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorInterceptor @Inject constructor(
    private val networkErrorCodeToExceptionMapper: NetworkErrorCodeToExceptionMapper,
    private val networkConnectionProvider: NetworkConnectionProvider,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkConnectionProvider.isConnected()) {
            throw NetworkException.NoInternetConnection()
        }
        val request = chain.request()
        val response = chain.proceed(request)

        when (response.code) {
            in (HttpURLConnection.HTTP_OK..HttpURLConnection.HTTP_RESET) -> {
                return response
            }

            else -> {
                val responseBody = response.body

                if (responseBody == null) {
                    throw NetworkException.ResponseBodyIsNull(httpStatusCode = response.code)
                } else {
                    throw getExceptionAccordingToResponseCode(
                        responseBody.string(),
                        response.code
                    )
                }
            }
        }

    }

    private fun getExceptionAccordingToResponseCode(
        errorMessage: String, responseCode: Int,
    ): IOException {
        throw networkErrorCodeToExceptionMapper.getException(
            errorMessage = errorMessage, responseCode = responseCode
        )
    }

}