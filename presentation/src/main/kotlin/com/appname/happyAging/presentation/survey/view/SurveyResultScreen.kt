package com.appname.happyAging.presentation.survey.view

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.appname.happyAging.presentation.common.layout.DefaultLayout
import com.appname.happyAging.presentation.common.state.UiState
import com.appname.happyAging.presentation.survey.viewmodel.SurveyViewModel

@Composable
fun SurveyResultScreen(
    onBackButtonClick: () -> Unit = {},
    surveyViewModel: SurveyViewModel = hiltViewModel(),
) {
    BackHandler(onBack = onBackButtonClick)
    val state = surveyViewModel.surveyResult.collectAsState()
    Log.d("SurveyResultScreen", "state: $state")
    DefaultLayout(
        title = "낙상위험도 측정결과",
        backButtonAction = onBackButtonClick,
    ) {
        when(state.value){
            is UiState.Error -> {
                Text(text = "Error")
            }
            UiState.Loading -> {
                Text(text = "Loading")
            }
            is UiState.Success ->{
                val result = (state.value as UiState.Success).data
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = result.summary
                    )
                }
            }
        }
    }
}