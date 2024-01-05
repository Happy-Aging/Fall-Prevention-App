package com.appname.happyAging.data.repository.survey

import com.appname.happyAging.data.api.ApiService
import com.appname.happyAging.domain.repository.survey.SurveyRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SurveyRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : SurveyRepository {

}