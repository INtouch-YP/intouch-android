package care.intouch.app.feature.plan.domain.di

import care.intouch.app.feature.plan.domain.usecase.GetAssignmentsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface PlanDomainModule {

    @Binds
    fun bindGetAssignmentsUseCase(impl: GetAssignmentsUseCase.Base): GetAssignmentsUseCase
}