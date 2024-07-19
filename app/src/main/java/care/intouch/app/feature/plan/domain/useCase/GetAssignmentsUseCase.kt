package care.intouch.app.feature.plan.domain.usecase

import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.plan.domain.api.PlanRepository
import care.intouch.app.feature.plan.domain.models.Assignment
import care.intouch.app.feature.plan.domain.models.AssignmentStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAssignmentsUseCase {
    suspend operator fun invoke(): Flow<Resource<List<Assignment>, ErrorEntity>>
    class Base @Inject constructor(
        private val planRepository: PlanRepository
    ) : GetAssignmentsUseCase {
        override suspend fun invoke(): Flow<Resource<List<Assignment>, ErrorEntity>> {
            return planRepository.getAssignments()
        }
    }
}

val mockAssignments: List<Assignment> = listOf(
    Assignment(
        id = 1,
        title = "Assignment 1",
        date = "19.06.2024",
        status = AssignmentStatus.TO_DO
    ),
    Assignment(
        id = 2,
        title = "Assignment 2",
        date = "20.06.2024",
        status = AssignmentStatus.TO_DO
    ),
    Assignment(
        id = 3,
        title = "Assignment 3",
        date = "21.06.2024",
        status = AssignmentStatus.IN_PROGRESS
    ),
    Assignment(
        id = 4,
        title = "Assignment 4",
        date = "22.06.2024",
        status = AssignmentStatus.IN_PROGRESS
    ),
    Assignment(
        id = 5,
        title = "Assignment 5",
        date = "23.06.2024",
        status = AssignmentStatus.DONE
    ),
    Assignment(
        id = 6,
        title = "Assignment 6",
        date = "24.06.2024",
        status = AssignmentStatus.DONE
    ),
    Assignment(
        id = 7,
        title = "Assignment 7",
        date = "25.06.2024",
        status = AssignmentStatus.DONE
    )
)