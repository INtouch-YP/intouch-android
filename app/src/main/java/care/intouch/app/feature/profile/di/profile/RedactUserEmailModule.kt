package care.intouch.app.feature.profile.di.profile

import care.intouch.app.feature.common.di.RetrofitWithAuth
import care.intouch.app.feature.profile.data.profile.api.RedactUserEmailApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RedactUserEmailModule {
    @Provides
    @Singleton
    fun provideRedactUserEmailApi(
        @RetrofitWithAuth retrofit: Retrofit
    ): RedactUserEmailApi {
        return retrofit.create(RedactUserEmailApi::class.java)
    }
}