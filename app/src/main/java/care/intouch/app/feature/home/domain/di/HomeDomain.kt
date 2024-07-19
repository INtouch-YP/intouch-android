package care.intouch.app.feature.home.domain.di

import care.intouch.app.feature.home.data.reposytories.AssignmentRepositoryImpl
import care.intouch.app.feature.home.data.reposytories.DiaryEntryRepositoryImpl
import care.intouch.app.feature.home.domain.AssignmentRepository
import care.intouch.app.feature.home.domain.DiaryEntryRepository
import care.intouch.app.feature.home.domain.use_case.AssignmentsInteractor
import care.intouch.app.feature.home.domain.use_case.DiaryEntriesInteractor
import care.intouch.app.feature.home.domain.use_case.GetDiaryEntries
import care.intouch.app.feature.home.domain.use_case.GetTasks
import care.intouch.app.feature.home.domain.use_case.GetUserInformation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeDomain {
    @Provides
    @Singleton
    fun providesAssignmentInteractor(
        interactor: AssignmentsInteractor.Base
    ): AssignmentsInteractor {
        return interactor
    }

    @Provides
    @Singleton
    fun providesDiaryEntriesInteractor(
        interactor: DiaryEntriesInteractor.Base
    ): DiaryEntriesInteractor {
        return interactor
    }

    @Provides
    @Singleton
    fun providesGetTasksUseCase(
        useCase: GetTasks.Base
    ): GetTasks {
        return useCase
    }

    @Provides
    @Singleton
    fun providesGetDiaryEntriesUseCase(
        useCase: GetDiaryEntries.Base
    ): GetDiaryEntries {
        return useCase
    }

    @Provides
    @Singleton
    fun providesGetUserInformationUseCase(
        useCase: GetUserInformation.Base
    ): GetUserInformation {
        return useCase
    }

    @Provides
    @Singleton
    fun providesAssignmentRepository(repository: AssignmentRepositoryImpl)
            : AssignmentRepository {
        return repository
    }

    @Provides
    @Singleton
    fun providesDiaryEntriesRepository(repository: DiaryEntryRepositoryImpl)
            : DiaryEntryRepository {
        return repository
    }

}