package care.intouch.app.feature.home.data.models

enum class StatusDto(val status: String) {
    TO_DO(status = "to do"),
    IN_PROGRESS(status = "in progress"),
    DONE(status = "done")
}