package care.intouch.app.core.navigation.navhost

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import care.intouch.app.core.navigation.Authentication
import care.intouch.app.core.navigation.MainNav
import care.intouch.app.core.navigation.PasswordChange
import care.intouch.app.core.navigation.PinCodeChange
import care.intouch.app.core.navigation.Profile
import care.intouch.app.core.navigation.ProfileRouteBranch
import care.intouch.app.core.navigation.SuccessfulPinCodeChange
import care.intouch.app.feature.pinCode.ui.change.PinCodeChangeScreen
import care.intouch.app.feature.pinCode.ui.change.SuccessfulPinCodeChangeScreen
import care.intouch.app.feature.profile.presentation.ui.profile.ui.ProfileScreen
import timber.log.Timber

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

        composable(
            route = Profile.route,
            deepLinks = listOf(navDeepLink {
                uriPattern = "https://app.intouch.care/email-update/{clientId}/{token}/"
            })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("clientId")
            val token = backStackEntry.arguments?.getString("token")
            Timber.tag("MY_INTOUCH_TAG").d("userId: $userId, token: $token")
            ProfileScreen(
                userId = userId,
                token = token,
                onSecurityClick = {
                    navController.navigate(route = PasswordChange.route)
                },
                onChangePinCode = {
                    navController.navigate(route = ProfileRouteBranch.route)
                },
                onSingOut = {
                    navController.navigate(route = Authentication.route)
                }
            )
        }
    }
}