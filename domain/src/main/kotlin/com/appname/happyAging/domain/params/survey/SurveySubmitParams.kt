package com.appname.happyAging.domain.params.survey

class SurveySubmitParams(
    val questionId: Long,
    val objectiveAnswerId: Long?,
    val subjectiveAnswer: String?
) {
    init {
        // 두개중 하나만 null이어야 함
        require((objectiveAnswerId == null) xor (subjectiveAnswer == null))
    }
}
