package com.appname.happyAging.domain.model.survey

import java.time.LocalDate

/**
 * 설문조사 결과 모델
 * @param resultId 설문조사 결과 id
 * @param date 설문조사 결과 날짜
 * @param rank 설문조사 결과 순위. fallRiskRank와 같은 의미이다.
 * @param summary 설문조사 결과 요약
 */
class SurveyResultModel(
    val resultId : Long,
    val date : LocalDate,
    val rank : Int,
    val summary: String,
)