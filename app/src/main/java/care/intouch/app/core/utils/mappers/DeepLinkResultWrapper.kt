package care.intouch.app.core.utils.mappers

sealed class DeepLinkResultWrapper {
    data object ResetPasswordDeepLink: DeepLinkResultWrapper()
    data object AbsentDeepLink: DeepLinkResultWrapper()
}