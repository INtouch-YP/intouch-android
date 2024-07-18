package care.intouch.app.feature.home.domain.models

import care.intouch.app.R as AppR

enum class Status(val stringId: Int) {
    TO_DO(stringId = AppR.string.to_do),
    IN_PROGRESS(stringId = AppR.string.in_progress),
    DONE(stringId = AppR.string.done),
    UNKNOWN(stringId = 0)
}