package care.intouch.app.feature.plan.data.api

import care.intouch.app.feature.authorization.data.models.mappers.UserExceptionToErrorMapper
import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.plan.data.network.NetworkAssignmentsSource
import care.intouch.app.feature.plan.data.network.dto.Request
import care.intouch.app.feature.plan.data.network.dto.model.mapToAssignment
import care.intouch.app.feature.plan.data.network.dto.response.AssignmentsResponse
import care.intouch.app.feature.plan.domain.api.PlanRepository
import care.intouch.app.feature.plan.domain.models.Assignment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlanRepositoryImpl(
    private val networkAssignmentsSource: NetworkAssignmentsSource,
    private val exceptionMapper: UserExceptionToErrorMapper
) : PlanRepository {
    override suspend fun getAssignments(): Flow<Resource<List<Assignment>, ErrorEntity>> = flow {
        try {
            val response = networkAssignmentsSource.doRequest(Request.AssignmentsRequest)
            val assignmentList = (response as AssignmentsResponse).assignments
                .map { assignmentDto ->  assignmentDto.mapToAssignment() }
            emit(Resource.Success(data = assignmentList))
        } catch (exception: Exception) {
            val error = exceptionMapper.handleException(exception)
            emit(Resource.Error(error = error))
        }
    }
}