package com.appname.happyAging.presentation.survey.view

import androidx.compose.runtime.Composable
import com.appname.happyAging.presentation.common.layout.DefaultLayout

@Composable
fun SurveyResultScreen(
    onBackButtonClick: () -> Unit = {},
) {
    DefaultLayout(
        title = "낙상위험도 측정결과",
        backButtonAction = onBackButtonClick,
    ) {

    }
}