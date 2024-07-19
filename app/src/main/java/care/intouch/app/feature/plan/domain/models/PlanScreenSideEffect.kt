package care.intouch.app.feature.plan.domain.models

import care.intouch.uikit.common.StringVO

sealed interface PlanScreenSideEffect {
    data class ShowToast(val message: StringVO) : PlanScreenSideEffect
}