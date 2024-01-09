package com.appname.happyAging.presentation.survey.view

import androidx.compose.runtime.Composable
import com.appname.happyAging.presentation.common.layout.DefaultLayout

@Composable
fun SurveyGuideScreen(
    onBackButtonClick: () -> Unit = {},
    onStartSurveyClick : () -> Unit = {}
) {
    DefaultLayout(
        title = "",
        backButtonAction = onBackButtonClick,
    ) {

    }
}