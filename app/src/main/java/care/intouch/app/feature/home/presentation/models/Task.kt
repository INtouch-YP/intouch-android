package care.intouch.app.feature.home.presentation.models

data class Task(
    val id: Int,
    val status: Status = Status.TO_DO,
    val isSharedWithDoctor: Boolean = false,
    val description: String = ""
)
