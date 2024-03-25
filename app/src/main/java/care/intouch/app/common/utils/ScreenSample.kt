package care.intouch.app.common.utils

sealed class ScreenSample {

    data object NavigationSample : ScreenSample()

    data object MainSampleMenu : ScreenSample()
}