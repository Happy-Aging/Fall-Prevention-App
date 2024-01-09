package com.appname.happyAging.data.dto.survey.response

import com.appname.happyAging.domain.model.survey.SurveyResultModel
import java.time.LocalDate

data class SurveyResultResponse(
    val resultId : Long,
    val date : LocalDate,
    val rank : Int,
    val summary: String,
)

fun SurveyResultResponse.toDomain() = SurveyResultModel(
    resultId = resultId,
    date = date,
    rank = rank,
    summary = summary,
)