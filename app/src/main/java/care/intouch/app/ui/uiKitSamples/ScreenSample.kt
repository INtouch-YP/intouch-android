package care.intouch.app.ui.uiKitSamples

sealed class ScreenSample {

    data object MainSampleMenu : ScreenSample()
    data object OneLineTexFieldSample : ScreenSample()
    data object MultilineTexFieldSample : ScreenSample()

    data object NavigationSample : ScreenSample()
    data object ToggleSample : ScreenSample()
}