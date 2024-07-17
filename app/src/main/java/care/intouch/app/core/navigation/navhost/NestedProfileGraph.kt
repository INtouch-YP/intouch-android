package care.intouch.app.core.navigation.navhost

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import care.intouch.app.core.navigation.MainNav
import care.intouch.app.core.navigation.PinCodeChange
import care.intouch.app.core.navigation.ProfileRouteBranch
import care.intouch.app.core.navigation.SuccessfulPinCodeChange
import care.intouch.app.feature.pinCode.ui.change.PinCodeChangeScreen
import care.intouch.app.feature.pinCode.ui.change.SuccessfulPinCodeChangeScreen

fun NavGraphBuilder.addNestedProfileGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = PinCodeChange.route, route = ProfileRouteBranch.route
    ) {

        composable(route = PinCodeChange.route) {

            PinCodeChangeScreen(
                onCloseClick = {
                    navController.navigate(route = MainNav.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                },
                onSuccessChange = {
                    navController.navigate(route = SuccessfulPinCodeChange.route)
                }
            )
        }

        composable(route = SuccessfulPinCodeChange.route) {
            SuccessfulPinCodeChangeScreen(
                onBackToHome = {
                    navController.navigate(route = MainNav.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}