package care.intouch.app.feature.profile.di.profile

import care.intouch.app.feature.common.di.RetrofitWithAuth
import care.intouch.app.feature.profile.data.profile.api.ConfirmEmailChangeApi
import care.intouch.app.feature.profile.data.profile.api.UpdateUserDataApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ConfirmEmailChangeModule {
    @Provides
    @Singleton
    fun provideConfirmEmailChangeApi(
        @RetrofitWithAuth retrofit: Retrofit
    ): ConfirmEmailChangeApi {
        return retrofit.create(ConfirmEmailChangeApi::class.java)
    }
}