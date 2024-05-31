package care.intouch.app.feature.authorization.data.di

import care.intouch.app.feature.authorization.data.api.UserApiService
import care.intouch.app.feature.common.di.RetrofitWithAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserNetworkModule {
    @Provides
    @Singleton
    fun provideUserApiService(
        @RetrofitWithAuth retrofit: Retrofit
    ): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }
}