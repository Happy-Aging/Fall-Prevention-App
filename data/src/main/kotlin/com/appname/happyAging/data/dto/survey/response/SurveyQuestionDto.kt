package com.appname.happyAging.data.dto.survey.response

data class SurveyQuestionDto(
    val questionId: Long,
    val number: String,
    val type: String,
    val options : List<SurveyQuestionOptionDto>
)

data class SurveyQuestionOptionDto(
    val optionId: Long,
    val number: String,
    val content: String,
    val image : String?,
)
