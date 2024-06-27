package care.intouch.app.core.navigation.navhost

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import care.intouch.app.core.navigation.Authentication
import care.intouch.app.core.navigation.AuthorizationRouteBranch
import care.intouch.app.core.navigation.MainNav
import care.intouch.app.core.navigation.AppNavScreen
import care.intouch.app.core.navigation.Authorization
import care.intouch.app.core.navigation.Home
import care.intouch.app.core.navigation.PasswordRecovery
import care.intouch.app.core.navigation.PinCodeConfirmation
import care.intouch.app.core.navigation.PinCodeEnter
import care.intouch.app.core.navigation.PinCodeInstallation
import care.intouch.app.core.navigation.Registration
import care.intouch.app.core.navigation.SendingNotification
import care.intouch.app.feature.authorization.presentation.AuthorizationScreen
import care.intouch.app.feature.authorization.presentation.ui.AuthenticationScreen
import care.intouch.app.feature.authorization.presentation.ui.EnterPinCodeScreen
import care.intouch.app.feature.authorization.presentation.ui.PasswordRecoveryScreen
import care.intouch.app.feature.authorization.presentation.ui.PinCodeConfirmationScreen
import care.intouch.app.feature.authorization.presentation.ui.PinCodeInstallationScreen
import care.intouch.app.feature.authorization.presentation.ui.RegistrationScreen
import care.intouch.app.feature.authorization.presentation.ui.SendingNotificationScreen

fun NavGraphBuilder.addNestedAuthorizationGraph(
    navController: NavHostController,
    startDestination: String?
) {
    navigation(
        startDestination = startDestination ?: Authentication.route,
        route = AuthorizationRouteBranch.route
    ) {

        composable(route = Registration.route) {
            RegistrationScreen(
                onSetPasswordClick = {
                    navController.navigate(route = PinCodeInstallation.route)
                }
            )
        }

        composable(route = PinCodeInstallation.route) {
            PinCodeInstallationScreen(
                onSaveClick = {
                    navController.navigate(route = PinCodeConfirmation.route)
                },
                onSkipClick = {
                    navController.navigate(route = MainNav.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = PinCodeConfirmation.route) {
            PinCodeConfirmationScreen(
                onSaveClick = {
                    navController.navigate(route = MainNav.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                },
                onSkipClick = {
                    navController.navigate(route = MainNav.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = PinCodeEnter.route) {
            EnterPinCodeScreen(
                onNextClick = {
                    navController.navigate(route = MainNav.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                },
                onForgotPicCodeClick = {
                    navController.navigate(route = Authentication.route)
                }
            )
        }

        composable(route = Authentication.route) {
            AuthenticationScreen(
                onForgotPasswordClick = {
                    navController.navigate(route = PasswordRecovery.route)
                },
                onLoginClick = {
                    navController.navigate(route = PinCodeInstallation.route)
                }
            )
        }

        composable(route = PasswordRecovery.route) {
            PasswordRecoveryScreen(
                onSendPasswordClick = {
                    navController.navigate(route = SendingNotification.route)
                }
            )
        }

        composable(route = SendingNotification.route) {
            SendingNotificationScreen(
                onGoBackClick = {
                    navController.navigate(route = Authentication.route)
                }
            )
        }

        composable(route = MainNav.route) {
            AppNavScreen(
                startDestination = Home.route
            )
        }

        composable(
            route = Authorization.route,
            deepLinks = listOf(navDeepLink {
                uriPattern = "https://app.intouch.care/activate-client/{clientId}/{token}/"
            })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("clientId")
            val token = backStackEntry.arguments?.getString("token")
            AuthorizationScreen(
                navController = navController,
                userId = userId,
                token = token,
                onGoPinCodeInstallationScreen = {
                    navController.navigate(route = PinCodeInstallation.route)
                }
            )
        }
    }
}