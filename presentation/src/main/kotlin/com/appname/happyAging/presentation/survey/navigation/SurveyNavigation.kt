package com.appname.happyAging.presentation.survey.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.appname.happyAging.presentation.survey.view.SurveyGuideScreen
import com.appname.happyAging.presentation.survey.view.SurveyResultScreen
import com.appname.happyAging.presentation.survey.view.SurveyScreen

const val SURVEY_GRAPH_ROUTE_PATTERN = "/senior/{id}/survey"
fun surveyGraphRoute(id: Long) = "/senior/$id/survey"
enum class SurveyRouter(
    val routePath: String,
    val korean: String,
) {
    SURVEY_GUIDE("survey-guide", "낙상 위험 측정 시작하기"),
    SURVEY("survey", "낙상 위험 측정"),
    SURVEY_RESULT("survey-result", "낙상 위험 측정 결과"),
}

@SuppressLint("UnrememberedGetBackStackEntry")
fun NavGraphBuilder.surveyGraph(
    navController: NavController,
    onOpenSourceClick: () -> Unit = {},
) {
    navigation(
        route = SURVEY_GRAPH_ROUTE_PATTERN,
        startDestination = SurveyRouter.SURVEY_GUIDE.routePath,
    ) {
        composable(route = SurveyRouter.SURVEY_GUIDE.routePath) {
            SurveyGuideScreen(
                onBackButtonClick = { navController.popBackStack() },
                onStartSurveyClick = {
                    navController.navigate(SurveyRouter.SURVEY.routePath){
                        popUpTo(SurveyRouter.SURVEY_GUIDE.routePath){
                            inclusive = true
                        }
                    }
                },
            )
        }
        composable(route = SurveyRouter.SURVEY.routePath) {
            val parentEntry = remember(it){navController.getBackStackEntry(SURVEY_GRAPH_ROUTE_PATTERN)}
            val seniorId = parentEntry.arguments?.getString("id")?.toLong()!!
            SurveyScreen(
                seniorId = seniorId,
                onSubmitButtonClick = {
                    navController.navigate(SurveyRouter.SURVEY_RESULT.routePath)
                }
            )
        }
        composable(route = SurveyRouter.SURVEY_RESULT.routePath) {
            val parentEntry = remember { navController.getBackStackEntry(SurveyRouter.SURVEY.routePath) }
            SurveyResultScreen(
                onBackButtonClick = {
                    navController.popBackStack(SURVEY_GRAPH_ROUTE_PATTERN, inclusive = true)
                },
                surveyViewModel = hiltViewModel(parentEntry),
            )
        }

    }
}