package care.intouch.app.core.navigation.navhost

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import care.intouch.app.core.navigation.AppNavScreen
import care.intouch.app.core.navigation.AssignmentsQuestion
import care.intouch.app.core.navigation.Plan
import care.intouch.app.core.navigation.PlanBottomNav
import care.intouch.app.core.navigation.PlanRouteBranch
import care.intouch.app.core.navigation.TaskCompleting
import care.intouch.app.core.navigation.TaskEstimate
import care.intouch.app.core.navigation.TaskIntroduction
import care.intouch.app.feature.plan.presentation.ui.TaskCompletingScreen
import care.intouch.app.feature.plan.presentation.ui.TaskEstimateScreen
import care.intouch.app.feature.questions.presentations.ui.introductory.IntroductoryQuestionsScreen

fun NavGraphBuilder.addNestedPlanGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = TaskIntroduction.route,
        route = PlanRouteBranch.route,
    ) {

        composable(route = TaskIntroduction.route) {
            IntroductoryQuestionsScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onNextClick = {
                    navController.navigate(route = AssignmentsQuestion.route)
                })
        }

        composable(route = TaskEstimate.route) {
            TaskEstimateScreen(
                onDoneClick = {
                    navController.navigate(route = PlanBottomNav.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                },
                onSkipClick = {
                    navController.navigate(route = PlanBottomNav.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = TaskCompleting.route) {
            TaskCompletingScreen(
                onCompleteTaskClick = {
                    navController.navigate(route = TaskEstimate.route)
                }
            )
        }

        composable(route = PlanBottomNav.route) {
            AppNavScreen(
                startDestination = Plan.route
            )
        }
    }
}