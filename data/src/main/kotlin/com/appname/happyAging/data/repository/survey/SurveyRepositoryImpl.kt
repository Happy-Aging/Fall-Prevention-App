package com.appname.happyAging.data.repository.survey

import com.appname.happyAging.data.api.ApiConstants
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
        return ApiResponse.Success(listOf(
            SurveyQuestionModel.fixture(),
            SurveyQuestionModel.fixture(),
            SurveyQuestionModel.fixture(),
        ))
        runCatching {
            val resp = apiService.getSurveyQuestionList()
            if(resp.isSuccessful){
                return@runCatching ApiResponse.Success(resp.body()!!.map { it.toDomain() })
            }
            return@runCatching ApiResponse.Error(resp.message())
        }.onSuccess {
            return it
        }.onFailure {
            return ApiResponse.Error(ApiConstants.ERROR)
        }
        return ApiResponse.Error(ApiConstants.UNKNOWN_ERROR)
    }

    override suspend fun submitSurvey(
        seniorId: Long,
        surveySubmitList: List<SurveySubmitParams>
    ): ApiResponse<SurveyResultModel> {
        return ApiResponse.Success(SurveyResultModel.fixture())
        runCatching {
            val resp = apiService.submitSurvey(seniorId,surveySubmitList.map { it.toData() })
            if(resp.isSuccessful){
                return@runCatching ApiResponse.Success(resp.body()!!.toDomain())
            }
            return@runCatching ApiResponse.Error(resp.message())
        }.onSuccess {
            return it
        }.onFailure {
            return ApiResponse.Error(ApiConstants.ERROR)
        }
        return ApiResponse.Error(ApiConstants.UNKNOWN_ERROR)
    }

    override suspend fun getPreviousSurveyResult(seniorId: Long): ApiResponse<List<SurveyResultModel>> {
        return ApiResponse.Success(listOf(
            SurveyResultModel.fixture(),
        ))
        runCatching {
            val resp = apiService.getPreviousSurveyResultList(seniorId)
            if(resp.isSuccessful){
                return@runCatching ApiResponse.Success(resp.body()!!.map { it.toDomain() })
            }
            return@runCatching ApiResponse.Error(resp.message())
        }.onSuccess {
            return it
        }.onFailure {
            return ApiResponse.Error(ApiConstants.ERROR)
        }
        return ApiResponse.Error(ApiConstants.UNKNOWN_ERROR)
    }
}