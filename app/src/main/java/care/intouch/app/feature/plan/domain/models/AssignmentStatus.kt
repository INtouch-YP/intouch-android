package care.intouch.app.feature.plan.domain.models

import care.intouch.app.R
import care.intouch.uikit.common.StringVO

enum class AssignmentStatus(val value: StringVO) {
    TO_DO(value = StringVO.Resource(resId = R.string.to_do)),
    IN_PROGRESS(value = StringVO.Resource(resId = R.string.in_progress)),
    DONE(value = StringVO.Resource(resId = R.string.done)),
    SHOW_ALL(value = StringVO.Resource(resId = R.string.see_all)),
    UNKNOWN(value = StringVO.Plain(text = ""))
}