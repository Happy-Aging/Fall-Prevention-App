package com.appname.happyAging.data.repository.survey

import com.appname.happyAging.data.api.ApiService
import com.appname.happyAging.data.dto.survey.request.toData
import com.appname.happyAging.data.dto.survey.response.toDomain
import com.appname.happyAging.domain.model.common.ApiResponse
import com.appname.happyAging.domain.model.survey.SurveyQuestionModel
import com.appname.happyAging.domain.model.survey.SurveyResultModel
import com.appname.happyAging.domain.params.survey.SurveySubmitParams
import com.appname.happyAging.domain.repository.survey.SurveyRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SurveyRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : SurveyRepository {
    override suspend fun getSurveyQuestionList(): ApiResponse<List<SurveyQuestionModel>> {
        val resp = apiService.getSurveyQuestionList()
        if(!resp.isSuccessful){
            return ApiResponse.Error(resp.message())
        }
        return kotlin.runCatching { //변환과정 중에 에러가 발생할 수 있으므로 runCatching을 사용
            ApiResponse.Success(resp.body()!!.map { it.toDomain() })
        }.getOrElse {
            ApiResponse.Error(it.message ?: "Internal Server Question Error 500")
        }
    }

    override suspend fun submitSurvey(
        seniorId: Long,
        surveySubmitList: List<SurveySubmitParams>
    ): ApiResponse<SurveyResultModel> {
        val resp = apiService.submitSurvey(seniorId,surveySubmitList.map { it.toData() })
        if(resp.isSuccessful){
            return ApiResponse.Error(resp.message())
        }
        return ApiResponse.Success(resp.body()!!.toDomain())
    }

    override suspend fun getPreviousSurveyResult(seniorId: Long): ApiResponse<List<SurveyResultModel>> {
        val resp = apiService.getPreviousSurveyResultList(seniorId)
        if(resp.isSuccessful){
            return ApiResponse.Success(resp.body()!!.map { it.toDomain() })
        }
        return ApiResponse.Error(resp.message())
    }
}