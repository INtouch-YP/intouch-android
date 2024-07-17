package care.intouch.app.core.navigation.navhost


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import care.intouch.app.core.navigation.AssignmentsQuestion
import care.intouch.app.core.navigation.IntroductoryQuestion
import care.intouch.app.core.navigation.QuestionsRouteBranch
import care.intouch.app.feature.questions.presentations.ui.introductory.IntroductoryQuestionsScreen
import care.intouch.app.feature.questions.presentations.ui.questions.QuestionsScreen

fun NavGraphBuilder.addNestedQuestionsGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = IntroductoryQuestion.route,
        route = QuestionsRouteBranch.route
    ) {
       composable(route = IntroductoryQuestion.route) {
           IntroductoryQuestionsScreen(
               onBackClick = {
                    navController.popBackStack()
               },
               onNextClick = {
                   navController.navigate(route = AssignmentsQuestion.route) {
                       popUpTo(navController.graph.startDestinationId) {
                           inclusive = true
                       }
                   }
               })
       }

        composable(route = AssignmentsQuestion.route) {
            QuestionsScreen(
                onCloseClick = {

                },
                onCompleteTaskClick = {

                })
        }
    }
}