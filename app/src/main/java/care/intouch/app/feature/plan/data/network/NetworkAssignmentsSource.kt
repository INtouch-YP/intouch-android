package care.intouch.app.feature.plan.data.network

import care.intouch.app.feature.plan.data.network.dto.Request
import care.intouch.app.feature.plan.data.network.dto.response.Response

interface NetworkAssignmentsSource {
    suspend fun doRequest(request: Request): Response
}