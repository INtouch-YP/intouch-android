package care.intouch.app.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import care.intouch.app.core.navigation.navhost.AppNavHost
import care.intouch.uikit.ui.navigation.CustomBottomNavBar
import care.intouch.uikit.ui.navigation.currentRoute


@Composable
fun AppNavScreen(
    startDestination: String,
    authStartDestination: String? = null
) {

    val screensWithBottomBar = listOf(
        Home.route, Plan.route, Diary.route, Profile.route
    )

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            if (screensWithBottomBar.contains(currentRoute(navController = navController))) {
                BottomBar(navController = navController)
            }
        }
    ) {

        Modifier.padding(it)

        AppNavHost(
            navController = navController,
            startDestination = startDestination,
            authStartDestination = authStartDestination
        )
    }
}

@Composable
fun BottomBar(
    navController: NavHostController
) {

    CustomBottomNavBar(
        screenRoute1 = Home.route,
        screenRoute2 = Plan.route,
        screenRoute3 = "", // TODO This screenRoure for Plus Button
        screenRoute4 = Diary.route,
        screenRoute5 = Profile.route,
        currentRoute = currentRoute(navController = navController),
        firstItemClick = {
            navController.navigate(Home.route)  {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        secondItemClick = {
            navController.navigate(Plan.route)  {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        thirdItemClick = {
            navController.navigate(Diary.route)  {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        fourthItemClick = {
            navController.navigate(Profile.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
    )
}