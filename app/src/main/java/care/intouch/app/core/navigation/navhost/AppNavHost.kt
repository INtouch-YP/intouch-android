package care.intouch.app.core.navigation.navhost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import care.intouch.app.core.navigation.Authentication
import care.intouch.app.core.navigation.Diary
import care.intouch.app.core.navigation.DiaryRouteBranch
import care.intouch.app.core.navigation.Home
import care.intouch.app.core.navigation.PasswordChange
import care.intouch.app.core.navigation.Plan
import care.intouch.app.core.navigation.PlanBottomNav
import care.intouch.app.core.navigation.Profile
import care.intouch.app.core.navigation.ProfileRouteBranch
import care.intouch.app.core.navigation.QuestionsRouteBranch
import care.intouch.app.feature.diary.DiaryNoteScreen
import care.intouch.app.feature.home.presentation.ui.HomeScreen
import care.intouch.app.feature.plan.presentation.ui.PlanScreen
import care.intouch.app.feature.profile.presentation.ui.profile.ui.ProfileScreen
import care.intouch.app.feature.profile.presentation.ui.security.SecurityScreenInit

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String,
    authStartDestination: String?,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier
    ) {
        composable(route = Home.route) {
            HomeScreen(
                onSeeAllPlanClicked = {
                    navController.navigate(route = PlanBottomNav.route)
                },
                onSeeAllDiaryClicked = {
                    navController.navigate(route = Diary.route)
                }
            )
        }

        composable(route = Plan.route) {
            PlanScreen(
                onTaskListItemClick = {
                    navController.navigate(route = QuestionsRouteBranch.route/*PlanRouteBranch.route*/)
                },
                onBackArrowClick = {
                    navController.navigate(route = Home.route)
                }
            )
        }

        composable(route = Diary.route) {
            DiaryNoteScreen(
                onMakeNoteClick = {
                    navController.navigate(route = DiaryRouteBranch.route)
                },
                onBackButtonClick = {
                    navController.navigate(route = Home.route)
                }
            )
        }

        composable(route = Profile.route) {
            ProfileScreen(
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


        composable(route = PasswordChange.route) {
            SecurityScreenInit(
                onPopBackStack = {
                    navController.popBackStack()
                },
                onDeleteProfileForeverClick = {
                    navController.navigate(route = Authentication.route)
                }
            )
        }

        addNestedAuthorizationGraph(
            navController = navController,
            startDestination = authStartDestination
        )

        addNestedPlanGraph(navController = navController)

        addNestedDiaryGraph(navController = navController)

        addNestedProfileGraph(navController = navController)

        addNestedQuestionsGraph(
            navController = navController
        )
    }
}


