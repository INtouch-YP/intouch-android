package care.intouch.app.feature.authorization.domain.useCase

interface ClearAccountUC {
    suspend operator fun invoke()
}