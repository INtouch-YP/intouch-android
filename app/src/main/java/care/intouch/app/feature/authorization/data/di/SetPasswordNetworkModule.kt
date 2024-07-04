package care.intouch.app.feature.authorization.data.di

import care.intouch.app.feature.authorization.data.api.SetPasswordApiService
import care.intouch.app.feature.common.di.RetrofitWithAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SetPasswordNetworkModule {
    @Provides
    @Singleton
    fun providePasswordApiService(
        @RetrofitWithAuth retrofit: Retrofit
    ): SetPasswordApiService {
        return retrofit.create(SetPasswordApiService::class.java)
    }
}