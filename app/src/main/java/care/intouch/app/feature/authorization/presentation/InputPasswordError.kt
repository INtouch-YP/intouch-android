package care.intouch.app.feature.authorization.presentation

sealed class InputPasswordError {
    data object NotMatch: InputPasswordError()
}
