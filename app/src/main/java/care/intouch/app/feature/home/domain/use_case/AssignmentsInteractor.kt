package care.intouch.app.feature.home.domain.use_case

import care.intouch.app.feature.home.domain.AssignmentRepository
import javax.inject.Inject

interface AssignmentsInteractor {
    suspend fun clearAssignment(assignmentId: Int): Result<String>
    suspend fun shareTaskWithDoctor(assignmentId: Int): Result<String>

    class Base @Inject constructor(private val repository: AssignmentRepository) :
        AssignmentsInteractor {
        override suspend fun clearAssignment(assignmentId: Int): Result<String> {
           return repository.clearAssignment(assignmentId = assignmentId)
        }

        override suspend fun shareTaskWithDoctor(assignmentId: Int): Result<String> {
           return repository.setAssignmentVisible(assignmentId = assignmentId)
        }
    }
}