package com.appname.happyAging.data.dto.survey.request

import com.appname.happyAging.domain.params.survey.SurveySubmitParams

data class SurveySubmitDto(
    val questionId: Long,
    val choiceId: Long?,
    val subjectiveResponse: String?
){
    init {
        // 두개중 하나만 null이어야 함
        require((choiceId == null) xor (subjectiveResponse == null))
    }
}

fun SurveySubmitParams.toData() = SurveySubmitDto(
    questionId = questionId,
    choiceId = objectiveAnswerId,
    subjectiveResponse = subjectiveAnswer,
)