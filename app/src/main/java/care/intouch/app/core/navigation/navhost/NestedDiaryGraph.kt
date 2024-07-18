package care.intouch.app.core.navigation.navhost

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import care.intouch.app.core.navigation.CreatingNoteIntroduction
import care.intouch.app.core.navigation.Diary
import care.intouch.app.core.navigation.DiaryEntries
import care.intouch.app.core.navigation.DiaryRouteBranch
import care.intouch.app.core.navigation.EmotionChoice
import care.intouch.app.feature.diary.CreatingNoteIntroductionScreen
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.EmotionChoiceScreen
import care.intouch.app.feature.diary.presentation.ui.fillingOutScreen.FillingOutScreen

fun NavGraphBuilder.addNestedDiaryGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = CreatingNoteIntroduction.route,
        route = DiaryRouteBranch.route
    ) {
        composable(route = CreatingNoteIntroduction.route) {
            CreatingNoteIntroductionScreen(
                onNextClick = {
                    navController.navigate(route = DiaryEntries.route)
                },
                onBackButtonClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = DiaryEntries.route) {
            FillingOutScreen(
                onNextClick = {
                    navController.navigate(route = EmotionChoice.route)
                },
                onBackClick = {
                    navController.popBackStack()
                },
                onSaveClick = {
                    navController.navigate(route = Diary.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                },
                viewModel = hiltViewModel()
            )
        }

        composable(route = EmotionChoice.route) {
            EmotionChoiceScreen(
                onSaveClick = {
                    navController.popBackStack()
                },
                onBackClick = {
                    navController.popBackStack()
                },
                viewModel = hiltViewModel()
            )
        }
    }
}