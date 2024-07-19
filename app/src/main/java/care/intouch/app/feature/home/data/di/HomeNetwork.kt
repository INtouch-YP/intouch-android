package care.intouch.app.feature.home.data.di

import care.intouch.app.feature.common.di.RetrofitWithAuth
import care.intouch.app.feature.home.data.api.AssignmentsApi
import care.intouch.app.feature.home.data.api.DiaryNotesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeNetwork {
    @Provides
    @Singleton
    fun providesTasksApi(
        @RetrofitWithAuth retrofit: Retrofit
    ): AssignmentsApi {
        return retrofit.create(AssignmentsApi::class.java)
    }

    @Provides
    @Singleton
    fun providesDiaryEntriesApi(
        @RetrofitWithAuth retrofit: Retrofit
    ): DiaryNotesApi {
        return retrofit.create(DiaryNotesApi::class.java)
    }

}