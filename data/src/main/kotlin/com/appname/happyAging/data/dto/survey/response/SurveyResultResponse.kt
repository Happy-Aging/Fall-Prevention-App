package com.appname.happyAging.data.dto.survey.response

import java.time.LocalDate

data class SurveyResultResponse(
    val resultId : Long,
    val date : LocalDate,
    val rank : Int,
    val summary: String,
)
