package care.intouch.app.feature.authorization.data.di

import care.intouch.app.feature.authorization.data.api.AuthenticationApiService
import care.intouch.app.feature.common.di.RetrofitWithoutAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthenticationNetworkModule {
    @Provides
    @Singleton
    fun provideAuthenticationApiService(
        @RetrofitWithoutAuth retrofit: Retrofit
    ): AuthenticationApiService {
        return retrofit.create(AuthenticationApiService::class.java)
    }
}