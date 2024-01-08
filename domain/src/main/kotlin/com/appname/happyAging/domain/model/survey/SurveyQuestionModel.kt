package com.appname.happyAging.domain.model.survey

/**
 * 설문조사 질문 모델. [options]가 빈 리스트일 경우, 주관식 문제이다.
 *
 * @param type 질문 유형. ui에 표시되기 위한 용도이다.
 */
class SurveyQuestionModel (
    val questionId: Long,
    val number: String,
    val type: String,
    val options : List<SurveyQuestionOptionModel>
)

