package care.intouch.app.feature.plan.presentation.models

import care.intouch.app.feature.plan.domain.models.Assignment

data class PlanScreenState(
    val assignments: List<Assignment> = emptyList(),
    val filteredAssignments: List<Assignment> = emptyList(),
    val isDialogueVisible: Boolean = false
)
