package com.appname.happyAging.domain.model.survey

/**
 * 설문조사 질문 모델. 객관식 문제일때 사용된다.
 * @param optionId 응답에 사용되는 선택지 id.
 * @param number 문제 번호. ui에 표시되기 위한 용도이다.
 * @param content 문제 내용
 * @param image 문제 이미지
 */
class SurveyQuestionOptionModel (
    val optionId: Long,
    val number: String,
    val content: String,
    val image : String?,
)