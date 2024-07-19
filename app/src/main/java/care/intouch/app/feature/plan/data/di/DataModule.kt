package care.intouch.app.feature.plan.data.di

import care.intouch.app.feature.authorization.data.models.mappers.UserExceptionToErrorMapper
import care.intouch.app.feature.common.di.RetrofitWithAuth
import care.intouch.app.feature.plan.data.api.PlanRepositoryImpl
import care.intouch.app.feature.plan.data.network.AssignmentsApiService
import care.intouch.app.feature.plan.data.network.NetworkAssignmentsSource
import care.intouch.app.feature.plan.data.network.NetworkAssignmentsSourceImpl
import care.intouch.app.feature.plan.domain.api.PlanRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun providesAssignmentsApiService(
        @RetrofitWithAuth retrofit: Retrofit
    ): AssignmentsApiService {
        return retrofit.create(AssignmentsApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesNetworkAssignmentsSource(apiService: AssignmentsApiService)
    : NetworkAssignmentsSource {
        return NetworkAssignmentsSourceImpl(apiService = apiService)
    }

    @Provides
    @Singleton
    fun providesPlanRepository(
        networkAssignmentsSource: NetworkAssignmentsSource,
        exceptionMapper: UserExceptionToErrorMapper
    )
    : PlanRepository {
        return PlanRepositoryImpl(
            networkAssignmentsSource = networkAssignmentsSource,
            exceptionMapper = exceptionMapper
        )
    }
}