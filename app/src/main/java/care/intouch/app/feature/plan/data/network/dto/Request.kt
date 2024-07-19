package care.intouch.app.feature.plan.data.network.dto

sealed class Request {
    data object AssignmentsRequest: Request()
}