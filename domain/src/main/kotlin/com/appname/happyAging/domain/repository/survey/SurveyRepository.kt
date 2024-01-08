package com.appname.happyAging.domain.repository.survey

import com.appname.happyAging.domain.model.common.ApiResponse
import com.appname.happyAging.domain.model.survey.SurveyQuestionModel
import com.appname.happyAging.domain.model.survey.SurveyResultModel
import com.appname.happyAging.domain.params.survey.SurveySubmitParams

interface SurveyRepository {
    suspend fun getSurveyQuestionList(): ApiResponse<List<SurveyQuestionModel>>
    suspend fun submitSurvey(surveySubmitList: List<SurveySubmitParams>): ApiResponse<SurveyResultModel>
    suspend fun getPreviousSurveyResult(seniorId : Long): ApiResponse<List<SurveyResultModel>>
}