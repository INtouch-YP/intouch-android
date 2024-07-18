package care.intouch.app.feature.home.domain.models

data class Task(
    val id: Int = 0,
    val status: Status = Status.UNKNOWN,
    val isSharedWithDoctor: Boolean = false,
    val description: String = ""
)
