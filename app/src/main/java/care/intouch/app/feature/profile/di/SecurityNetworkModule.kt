package care.intouch.app.feature.profile.di

import care.intouch.app.feature.common.di.RetrofitWithoutAuth
import care.intouch.app.feature.profile.data.network.SecurityApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SecurityNetworkModule {
    @Provides
    @Singleton
    fun provideSecurityApiService(
        @RetrofitWithoutAuth retrofit: Retrofit
    ): SecurityApiService {
        return retrofit.create(SecurityApiService::class.java)
    }
}