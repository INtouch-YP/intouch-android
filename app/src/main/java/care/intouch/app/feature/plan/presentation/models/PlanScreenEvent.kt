package care.intouch.app.feature.plan.presentation.models

import care.intouch.app.feature.plan.domain.models.AssignmentStatus

sealed class PlanScreenEvent {
    data object GetAssignmentsEvent : PlanScreenEvent()
    data class FilterAssignmentsEvent(val assignmentStatus: AssignmentStatus): PlanScreenEvent()
    data class SetDialogueVisibilityEvent(val isVisible: Boolean) : PlanScreenEvent()
}