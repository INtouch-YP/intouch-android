package care.intouch.app.feature.common.di

import android.content.Context
import care.intouch.app.feature.common.data.AuthInterceptor
import care.intouch.app.feature.common.data.ErrorInterceptor
import care.intouch.app.feature.common.data.api.NetworkConnectionProvider
import care.intouch.app.feature.common.data.api.TokensApiService
import care.intouch.app.feature.common.data.impl.NetworkConnectionProviderImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkhttpClientBuilder

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkhttpClientBuilderWithAuth

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkhttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkhttpClientWithAuth

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitWithoutAuth

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitWithAuth

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @OkhttpClientBuilder
    @Provides
    fun provideOkhttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Singleton
    @OkhttpClientBuilderWithAuth
    @Provides
    fun provideOkhttpClientBuilderWithAuth(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Singleton
    @OkhttpClient
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        errorInterceptor: ErrorInterceptor,
        @OkhttpClientBuilder okHttpClientBuilder: OkHttpClient.Builder
    ): OkHttpClient {
        return okHttpClientBuilder
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(errorInterceptor)
            .build()
    }

    @Singleton
    @OkhttpClientWithAuth
    @Provides
    fun provideOkHttpClientWithToken(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        errorInterceptor: ErrorInterceptor,
        @OkhttpClientBuilderWithAuth okHttpClientBuilder: OkHttpClient.Builder
    ): OkHttpClient {
        return okHttpClientBuilder
            .addInterceptor(errorInterceptor)
            .addInterceptor(authInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @RetrofitWithoutAuth
    fun provideRetrofitWithoutToken(
        @OkhttpClient okHttpClient: OkHttpClient, json: Json
    ): Retrofit {
        return Retrofit.Builder().baseUrl(ENDPOINT_URL).client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType())).build()
    }

    @Provides
    @RetrofitWithAuth
    fun provideMainRetrofitWithToken(
        @OkhttpClientWithAuth okHttpClient: OkHttpClient, json: Json
    ): Retrofit {
        return Retrofit.Builder().baseUrl(ENDPOINT_URL).client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType())).build()
    }

    @Provides
    @Singleton
    fun provideJson(): Json = Json { ignoreUnknownKeys = true }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideNetworkConnectionProvider(@ApplicationContext context: Context): NetworkConnectionProvider {
        return NetworkConnectionProviderImpl(context)
    }

    @Provides
    @Singleton
    fun provideTokenApiService(
        @RetrofitWithoutAuth retrofit: Retrofit
    ): TokensApiService {
        return retrofit.create(TokensApiService::class.java)
    }

    companion object {
        const val ENDPOINT_URL = "https://app.intouch.care/"
    }
}
