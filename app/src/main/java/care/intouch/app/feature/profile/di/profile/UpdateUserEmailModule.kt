package care.intouch.app.feature.profile.di.profile

import care.intouch.app.feature.common.di.RetrofitWithAuth
import care.intouch.app.feature.profile.data.profile.api.UpdateUserEmailApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UpdateUserEmailModule {
    @Provides
    @Singleton
    fun provideRedactUserEmailApi(
        @RetrofitWithAuth retrofit: Retrofit
    ): UpdateUserEmailApi {
        return retrofit.create(UpdateUserEmailApi::class.java)
    }
}