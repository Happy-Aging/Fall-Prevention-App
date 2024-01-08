package com.appname.happyAging.data.dto.survey.response

import com.appname.happyAging.domain.model.survey.SurveyQuestionModel
import com.appname.happyAging.domain.model.survey.SurveyQuestionOptionModel

data class SurveyQuestionDto(
    val questionId: Long,
    val number: String,
    val content: String,
    val type: String,
    val isSubject : Boolean,
    val options : List<SurveyQuestionOptionDto>
){
    init {
        require(options.isNotEmpty() xor isSubject)
    }
}

data class SurveyQuestionOptionDto(
    val optionId: Long,
    val number: String,
    val content: String,
    val image : String?,
)

fun SurveyQuestionDto.toDomain() = SurveyQuestionModel(
    questionId = questionId,
    number = number,
    content = content,
    type = type,
    isSubject = isSubject,
    options = options.map { it.toDomain() }
)

fun SurveyQuestionOptionDto.toDomain() = SurveyQuestionOptionModel(
    optionId = optionId,
    number = number,
    content = content,
    image = image,
)