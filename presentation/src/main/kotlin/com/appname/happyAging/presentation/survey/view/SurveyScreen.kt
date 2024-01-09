package com.appname.happyAging.presentation.survey.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.appname.happyAging.domain.model.survey.SurveyQuestionOptionModel
import com.appname.happyAging.domain.params.survey.SurveySubmitParams
import com.appname.happyAging.presentation.common.component.CommonButton
import com.appname.happyAging.presentation.common.component.CustomTextEditField
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles
import com.appname.happyAging.presentation.common.layout.DefaultLayout
import com.appname.happyAging.presentation.common.state.UiState
import com.appname.happyAging.presentation.survey.viewmodel.SurveyViewModel

@Composable
fun SurveyScreen(
    surveyViewModel: SurveyViewModel = hiltViewModel(),
    onSubmitButtonClick : () -> Unit = {}
) {
    val state = surveyViewModel.survey.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    var currentQuestionIndex by rememberSaveable { mutableStateOf(0) }
    var isAnswerSelected by rememberSaveable { mutableStateOf(true) }

    LaunchedEffect(isAnswerSelected){//답변 미선택시 snackbar
        if(!isAnswerSelected){
            snackbarHostState.showSnackbar(
                message = "답변을 선택해주세요.",
                duration = SnackbarDuration.Short,
            )
        }
    }
    DefaultLayout(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {
        Column {
            when(state.value){
                is UiState.Error -> {
                    //TODO
                }
                UiState.Loading -> {
                    //TODO
                }
                is UiState.Success -> {
                    val questionList = (state.value as UiState.Success).data
                    val currentQuestion = questionList[currentQuestionIndex]
                    val submitAnswerList : MutableList<SurveySubmitParams?> = List(questionList.size) { null }.toMutableList()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = Sizes.DEFAULT_PADDING,
                                vertical = 13.dp,
                            )
                    ) {
                        Text(
                            text = currentQuestion.type,
                            style = TextStyles.TITLE_LARGE2
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = currentQuestion.number,
                            style = TextStyles.TITLE_MEDIUM2
                        )
                    }
                    LinearProgressIndicator(
                        progress = (currentQuestionIndex + 1) / questionList.size.toFloat(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = Sizes.DEFAULT_PADDING)
                            .verticalScroll(rememberScrollState())
                    ) {
                        QuestionContent(
                            content = currentQuestion.content,
                            isSubject = currentQuestion.isSubject,
                            options = currentQuestion.options,
                            onSubjectAnswerChange = {
                                submitAnswerList[currentQuestionIndex] = SurveySubmitParams(
                                    questionId = currentQuestion.questionId,
                                    objectiveAnswerId = null,
                                    subjectiveAnswer = it
                                )
                            },
                            onObjectiveAnswerChange = {
                                submitAnswerList[currentQuestionIndex] = SurveySubmitParams(
                                    questionId = currentQuestion.questionId,
                                    objectiveAnswerId = it,
                                    subjectiveAnswer = null
                                )
                            },
                        )
                        Spacer(modifier = Modifier.height(23.dp))
                        CommonButton(
                            text = if(currentQuestionIndex == questionList.size - 1)
                                "제출 하기" else "다음 문제",
                            color = Colors.DARK_BLACK,
                            onClick = {
                                if(currentQuestionIndex == questionList.size - 1){
                                    //TODO
                                    onSubmitButtonClick()
                                }else{
                                    if(submitAnswerList[currentQuestionIndex] != null){ //답변을 제출했을 경우
                                        currentQuestionIndex++
                                        isAnswerSelected = true
                                    }else{
                                        isAnswerSelected = false
                                    }
                                }
                            },
                        )
                    }

                }
            }
        }

    }
}

@Composable
internal fun QuestionContent(
    content: String,
    isSubject : Boolean,
    options : List<SurveyQuestionOptionModel>,
    onSubjectAnswerChange : (String) -> Unit = {},
    onObjectiveAnswerChange : (Long) -> Unit = {},
){
    Spacer(modifier = Modifier.height(Sizes.INTERVAL_LARGE3))
    Text(
        text =content,
        style = TextStyles.TITLE_LARGE1
    )
    Spacer(modifier = Modifier.height(Sizes.INTERVAL_LARGE3))
    if(isSubject) { //주관식 문제
        var answer by rememberSaveable { mutableStateOf("") }
        CustomTextEditField(value = answer, onValueChange = {
            answer = it
            onSubjectAnswerChange(it)
        })
    }else{ //객관식 문제
        var selectedOptionId : Long? by rememberSaveable { mutableStateOf(null) }
        options.forEach {
            Row(
                modifier = Modifier
                    .shadow(
                        elevation = 1.dp,
                        spotColor = Color(0x40898989),
                        ambientColor = Color(0x40898989)
                    )
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(color = Color(0xFFF9F9F9), shape = RoundedCornerShape(size = 5.dp))
                    .clickable {
                        selectedOptionId = it.optionId
                        onObjectiveAnswerChange(it.optionId)
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.width(Sizes.INTERVAL0))
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            color = Color.White,
                            shape = CircleShape
                        )
                        .border(
                            width = 1.dp,
                            color = Color(0xFFD9D9D9),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ){
                    if(selectedOptionId == it.optionId){
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(
                                    color = Colors.DARK_BLACK,
                                    shape = CircleShape
                                )
                        )
                    }
                }
                Spacer(modifier = Modifier.width(Sizes.INTERVAL0))
                Text(
                    text = it.content,
                    style = TextStyles.CONTENT_TEXT2_STYLE
                )
            }
            Spacer(modifier = Modifier.height(17.dp))
        }
    }
}

@Preview
@Composable
fun QuestionContentPreview() {
    QuestionContent(
        content = "최근 3년간 낙상사고를 당한적이 있습니까?",
        isSubject = false,
        options = listOf(
            SurveyQuestionOptionModel.fixture(
                optionId = 1,
                number = "1",
                content = "1~5회",
            ),
            SurveyQuestionOptionModel.fixture(
                optionId = 2,
                number = "2",
                content = "6~10회",
            ),
            SurveyQuestionOptionModel.fixture(
                optionId = 3,
                number = "3",
                content = "11~15회",
            ),
            SurveyQuestionOptionModel.fixture(
                optionId = 4,
                number = "4",
                content = "없다.",
            ),
        ),
    )
}