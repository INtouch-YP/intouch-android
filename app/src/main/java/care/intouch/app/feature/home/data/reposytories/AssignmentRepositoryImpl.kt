package care.intouch.app.feature.home.data.reposytories

import care.intouch.app.feature.home.data.api.AssignmentsApi
import care.intouch.app.feature.home.data.mappers.HomeMapper
import care.intouch.app.feature.home.domain.AssignmentRepository
import care.intouch.app.feature.home.domain.models.Task
import javax.inject.Inject

class AssignmentRepositoryImpl @Inject constructor(
    private val homeApi: AssignmentsApi,
    private val mapper: HomeMapper
) : AssignmentRepository {
    override suspend fun getAssignments(userId: Int): Result<List<Task>> {
        try {
            val request = mapper.mapAssignmentsRequest(userId = userId)
            val response = homeApi.getClientsAssignments(request)

            return Result.success(mapper.mapAssignments(response))
        } catch (exception: Exception) {
            return Result.failure(exception)
        }
    }

    override suspend fun clearAssignment(assignmentId: Int): Result<String> {
        try {
            val response = homeApi.clearAssignment(id = assignmentId)
            return Result.success(response.message)

        } catch (exception: Exception) {
            return Result.failure(exception)
        }
    }

    override suspend fun setAssignmentVisible(assignmentId: Int): Result<String> {
        try {
            val response = homeApi.setAssignmentVisible(id = assignmentId)
            return Result.success(response.message)

        } catch (exception: Exception) {
            return Result.failure(exception)
        }
    }

    override suspend fun duplicateAssignment(taskId: Int): Result<String> {
        return Result.success("")
    }
}