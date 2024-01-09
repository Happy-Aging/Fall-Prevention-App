package com.appname.happyAging.presentation.survey.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appname.happyAging.domain.model.common.ApiResponse
import com.appname.happyAging.domain.model.common.onFailure
import com.appname.happyAging.domain.model.common.onSuccess
import com.appname.happyAging.domain.model.survey.SurveyQuestionModel
import com.appname.happyAging.domain.model.survey.SurveyResultModel
import com.appname.happyAging.domain.params.survey.SurveySubmitParams
import com.appname.happyAging.domain.repository.survey.SurveyRepository
import com.appname.happyAging.presentation.common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SurveyViewModel @Inject constructor(
    private val surveyRepository: SurveyRepository,
): ViewModel() {
    val survey: StateFlow<UiState<List<SurveyQuestionModel>>> get() = _survey
    private val _survey = MutableStateFlow<UiState<List<SurveyQuestionModel>>>(UiState.Loading)

    init {
        getSurvey()
    }

    fun getSurvey() {
        viewModelScope.launch {
            surveyRepository.getSurveyQuestionList()
                .onSuccess {
                    _survey.value = UiState.Success(it)
                }.onFailure {
                    _survey.value = UiState.Error(it)
                }
        }
    }

    suspend fun submitSurvey(
        seniorId : Long,
        answerList : List<SurveySubmitParams>
    ) : ApiResponse<SurveyResultModel> {
        return surveyRepository.submitSurvey(
            seniorId = seniorId,
            surveySubmitList = answerList
        )
    }
}