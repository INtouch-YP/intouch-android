package care.intouch.app.feature.profile.di.profile

import care.intouch.app.feature.common.di.RetrofitWithAuth
import care.intouch.app.feature.profile.data.profile.api.RedactUserDataApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RedactUserDataModule {
    @Provides
    @Singleton
    fun provideRedactUserDataApi(
        @RetrofitWithAuth retrofit: Retrofit
    ): RedactUserDataApi {
        return retrofit.create(RedactUserDataApi::class.java)
    }
}