package care.intouch.app.core.navigation.navhost

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import care.intouch.app.core.navigation.Authentication
import care.intouch.app.core.navigation.ButtonSample2
import care.intouch.app.core.navigation.ButtonsSample
import care.intouch.app.core.navigation.CardSample
import care.intouch.app.core.navigation.CheckboxSample
import care.intouch.app.core.navigation.CheckmarkSample
import care.intouch.app.core.navigation.ChipsSample
import care.intouch.app.core.navigation.DebugMode
import care.intouch.app.core.navigation.MainNav
import care.intouch.app.core.navigation.MultilineTextFieldSample
import care.intouch.app.core.navigation.Navigation
import care.intouch.app.core.navigation.NavigationSample
import care.intouch.app.core.navigation.OnEmotionClick
import care.intouch.app.core.navigation.OneLineTextFieldSample
import care.intouch.app.core.navigation.PasswordTextFieldSample
import care.intouch.app.core.navigation.PinCodeEnter
import care.intouch.app.core.navigation.PinCodeSample
import care.intouch.app.core.navigation.ProgressBarSample
import care.intouch.app.core.navigation.Registration
import care.intouch.app.core.navigation.Sample
import care.intouch.app.core.navigation.SliderSample
import care.intouch.app.core.navigation.ToggleSample
import care.intouch.app.ui.uiKitSamples.screens.DebugModeScreen
import care.intouch.app.ui.uiKitSamples.screens.NavigationScreen
import care.intouch.app.ui.uiKitSamples.screens.SampleScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    isAuthenticate: Boolean = false
) {
    NavHost(
        navController = navController,
        startDestination = DebugMode.route
    ) {

        composable(route = DebugMode.route) {
            DebugModeScreen(
                onUISamplesButtonClick = {
                    navController.navigate(route = Sample.route)
                },
                onNavigationButtonClick = {
                    navController.navigate(route = Navigation.route)
                },
                onApplicationFlowButtonClick = {
                    if (isAuthenticate) {
                        navController.navigate(route = MainNav.route)
                    } else {
                        navController.navigate(route = Authentication.route)
                    }
                }
            )
        }

        composable(route = Navigation.route) {
            NavigationScreen(
                goToAuthenticationScreen = { navController.navigate(route = Authentication.route) },
                goToRegistrationScreen = { navController.navigate(route = Registration.route) },
                goToPinCodeScreen = { navController.navigate(route = PinCodeEnter.route) },
                goToMainScreen = {
                    navController.navigate(route = MainNav.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = Sample.route) {
            SampleScreen(
                onButtonSampleClick = { navController.navigate(route = ButtonsSample.route) },

                onChipSampleClick = { navController.navigate(route = ChipsSample.route) },

                onMultilineTextFieldSampleClick = {
                    navController.navigate(route = MultilineTextFieldSample.route)
                },

                onNavigationSampleClick = {
                    navController.navigate(route = NavigationSample.route)
                },

                onOneLineTextFieldSampleClick = {
                    navController.navigate(route = OneLineTextFieldSample.route)
                },

                onPasswordTextFieldSampleClick = {
                    navController.navigate(route = PasswordTextFieldSample.route)
                },

                onSliderSampleClick = { navController.navigate(route = SliderSample.route) },

                onToggleSampleClick = { navController.navigate(route = ToggleSample.route) },

                onProgressBarSampleClick = { navController.navigate(route = ProgressBarSample.route) },

                onPinCodeSampleClick = { navController.navigate(route = PinCodeSample.route) },

                onCheckmarkSampleClick = { navController.navigate(route = CheckmarkSample.route) },

                onCheckboxSampleClick = { navController.navigate(route = CheckboxSample.route) },

                onCardSampleClick = { navController.navigate(route = CardSample.route) },

                onButtonSample2Click = { navController.navigate(route = ButtonSample2.route) },
                onEmotionClick = { navController.navigate(route = OnEmotionClick.route) }
            )
        }

        addNestedAuthorizationGraph(
            navController = navController,
            startDestination = null
        )

        addNestedSamplesGraph()
    }
}