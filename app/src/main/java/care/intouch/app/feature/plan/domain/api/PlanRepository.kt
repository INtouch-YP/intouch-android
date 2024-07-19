package care.intouch.app.feature.plan.domain.api

import care.intouch.app.feature.common.Resource
import care.intouch.app.feature.common.domain.errors.ErrorEntity
import care.intouch.app.feature.plan.domain.models.Assignment
import kotlinx.coroutines.flow.Flow

interface PlanRepository {
    suspend fun getAssignments(): Flow<Resource<List<Assignment>, ErrorEntity>>
}